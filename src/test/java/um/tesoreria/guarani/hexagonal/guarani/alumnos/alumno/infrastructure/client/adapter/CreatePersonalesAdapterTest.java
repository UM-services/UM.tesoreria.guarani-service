package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.AlumnoGuaraniClient;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.PersonaCoreResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.model.PersonaDocumentoGuarani;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePersonalesAdapterTest {

    private static final Integer ALUMNO = 7;
    private static final Integer GUARANI_PERSONA = 4500;
    private static final BigDecimal PERSONA_ID = new BigDecimal("1234567");

    @Mock
    private AlumnoGuaraniClient alumnoGuaraniClient;

    @InjectMocks
    private CreatePersonalesAdapter adapter;

    @Test
    void sendsNumericDocumentAndDocumentLettersToCore() {
        var alumno = alumno("AA1234567B");
        var esperado = CreatePersonalesResponse.builder()
                .result(true)
                .persona(PersonaCoreResponse.builder().uniqueId(99L).personaId(PERSONA_ID).documentoId(1).build())
                .build();
        when(alumnoGuaraniClient.createPersonales(alumno)).thenReturn(esperado);

        var response = adapter.createPersonales(alumno);

        ArgumentCaptor<AlumnoGuarani> captor = ArgumentCaptor.forClass(AlumnoGuarani.class);
        verify(alumnoGuaraniClient).createPersonales(captor.capture());
        var personaRel = captor.getValue().getPersonaRel();
        assertThat(personaRel.getDocumentoPrincipalRel().getNroDocumento()).isEqualTo("1234567");
        assertThat(personaRel.getNumeroPrefijo()).isEqualTo("AA");
        assertThat(personaRel.getNumeroPosfijo()).isEqualTo("B");
        assertThat(personaRel.getPersona()).isEqualTo(GUARANI_PERSONA);
        assertThat(response).isSameAs(esperado);
    }

    @Test
    void sendsEmptySuffixesWhenTheDocumentHasNoLetters() {
        var alumno = alumno("1234567");
        when(alumnoGuaraniClient.createPersonales(any(AlumnoGuarani.class))).thenReturn(CreatePersonalesResponse.builder().result(true).build());

        adapter.createPersonales(alumno);

        ArgumentCaptor<AlumnoGuarani> captor = ArgumentCaptor.forClass(AlumnoGuarani.class);
        verify(alumnoGuaraniClient).createPersonales(captor.capture());
        var personaRel = captor.getValue().getPersonaRel();
        assertThat(personaRel.getDocumentoPrincipalRel().getNroDocumento()).isEqualTo("1234567");
        assertThat(personaRel.getNumeroPrefijo()).isEmpty();
        assertThat(personaRel.getNumeroPosfijo()).isEmpty();
    }

    @Test
    void completesGuaraniPersonaFromTheAlumnoWhenThePersonaHasNoId() {
        var alumno = alumno("1234567");
        alumno.getPersonaRel().setPersona(null);
        when(alumnoGuaraniClient.createPersonales(any(AlumnoGuarani.class))).thenReturn(CreatePersonalesResponse.builder().result(true).build());

        adapter.createPersonales(alumno);

        assertThat(alumno.getPersonaRel().getPersona()).isEqualTo(GUARANI_PERSONA);
    }

    @Test
    void doesNotCallCoreWhenTheDocumentHasNoDigits() {
        var alumno = alumno("ABC");

        var response = adapter.createPersonales(alumno);

        verifyNoInteractions(alumnoGuaraniClient);
        assertThat(response.getResult()).isFalse();
        assertThat(response.getAlumnoGuarani()).isSameAs(alumno);
        assertThat(alumno.getPersonaRel().getDocumentoPrincipalRel().getNroDocumento()).isEqualTo("ABC");
        assertThat(alumno.getPersonaRel().getNumeroPrefijo()).isNull();
    }

    @Test
    void doesNotCallCoreWhenThePrincipalDocumentIsMissing() {
        var alumno = AlumnoGuarani.builder()
                .alumno(ALUMNO)
                .persona(GUARANI_PERSONA)
                .personaRel(PersonaGuarani.builder().persona(GUARANI_PERSONA).build())
                .build();

        var response = adapter.createPersonales(alumno);

        verifyNoInteractions(alumnoGuaraniClient);
        assertThat(response.getResult()).isFalse();
    }

    @Test
    void returnsFailedResponseWhenCoreDoesNotAnswer() {
        var alumno = alumno("1234567");
        when(alumnoGuaraniClient.createPersonales(alumno)).thenReturn(null);

        var response = adapter.createPersonales(alumno);

        assertThat(response.getResult()).isFalse();
        assertThat(response.getAlumnoGuarani()).isSameAs(alumno);
    }

    private AlumnoGuarani alumno(String nroDocumento) {
        return AlumnoGuarani.builder()
                .alumno(ALUMNO)
                .persona(GUARANI_PERSONA)
                .personaRel(PersonaGuarani.builder()
                        .persona(GUARANI_PERSONA)
                        .documentoPrincipalRel(PersonaDocumentoGuarani.builder()
                                .nroDocumento(nroDocumento)
                                .build())
                        .build())
                .build();
    }
}
