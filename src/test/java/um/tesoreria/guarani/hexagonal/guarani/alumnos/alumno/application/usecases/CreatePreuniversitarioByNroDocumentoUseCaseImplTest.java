package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePersonalesByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.AlumnoGuaraniClient;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.PersonaCoreResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.dto.PropuestaResponsableAcademicaGuaraniResponse;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePreuniversitarioByNroDocumentoUseCaseImplTest {

    private static final String DOCUMENTO = "AA1234567";

    @Mock
    private CreatePersonalesByNroDocumentoUseCase createPersonalesByNroDocumentoUseCase;

    @Mock
    private AlumnoGuaraniClient alumnoGuaraniClient;

    @InjectMocks
    private CreatePreuniversitarioByNroDocumentoUseCaseImpl useCase;

    @Test
    void sendsCompletePersonalesToCore() {
        var personales = personales(PersonaCoreResponse.builder().uniqueId(99L).personaId(new BigDecimal("1234567")).documentoId(1).build(),
                propuesta(List.of(PropuestaResponsableAcademicaGuaraniResponse.builder().responsableAcademica(5).build())));
        var preuniversitario = AlumnoGuarani.builder().alumno(1).build();
        when(createPersonalesByNroDocumentoUseCase.createPersonalesByNroDocumento(DOCUMENTO)).thenReturn(List.of(personales));
        when(alumnoGuaraniClient.createPreuniversitario(personales)).thenReturn(preuniversitario);

        assertThat(useCase.createPreuniversitarioByNroDocumento(DOCUMENTO)).containsExactly(preuniversitario);
    }

    @Test
    void skipsAlumnosWhosePersonalesWereNotCreated() {
        var fallido = CreatePersonalesResponse.builder().result(false).alumnoGuarani(alumno()).build();
        when(createPersonalesByNroDocumentoUseCase.createPersonalesByNroDocumento(DOCUMENTO)).thenReturn(List.of(fallido));

        assertThat(useCase.createPreuniversitarioByNroDocumento(DOCUMENTO)).isEmpty();

        verifyNoInteractions(alumnoGuaraniClient);
    }

    @Test
    void skipsAlumnosWithoutAPersonaEvenWhenResultIsTrue() {
        var sinPersona = CreatePersonalesResponse.builder().result(true).alumnoGuarani(alumno()).propuestaGuarani(propuesta(List.of())).build();
        when(createPersonalesByNroDocumentoUseCase.createPersonalesByNroDocumento(DOCUMENTO)).thenReturn(List.of(sinPersona));

        assertThat(useCase.createPreuniversitarioByNroDocumento(DOCUMENTO)).isEmpty();

        verifyNoInteractions(alumnoGuaraniClient);
    }

    @Test
    void skipsAlumnosWithoutAcademicResponsibleBecauseCoreCannotResolveIt() {
        var sinResponsable = personales(PersonaCoreResponse.builder().uniqueId(99L).build(), propuesta(List.of()));
        when(createPersonalesByNroDocumentoUseCase.createPersonalesByNroDocumento(DOCUMENTO)).thenReturn(List.of(sinResponsable));

        assertThat(useCase.createPreuniversitarioByNroDocumento(DOCUMENTO)).isEmpty();

        verifyNoInteractions(alumnoGuaraniClient);
    }

    @Test
    void keepsProcessingTheRestWhenCoreFailsForOneAlumno() {
        var ok = personales(PersonaCoreResponse.builder().uniqueId(99L).build(), propuesta(List.of(PropuestaResponsableAcademicaGuaraniResponse.builder().responsableAcademica(5).build())));
        when(createPersonalesByNroDocumentoUseCase.createPersonalesByNroDocumento(DOCUMENTO)).thenReturn(List.of(ok));
        when(alumnoGuaraniClient.createPreuniversitario(any(CreatePersonalesResponse.class))).thenThrow(new IllegalStateException("core caído"));

        assertThat(useCase.createPreuniversitarioByNroDocumento(DOCUMENTO)).isEmpty();

        verify(alumnoGuaraniClient).createPreuniversitario(ok);
    }

    private CreatePersonalesResponse personales(PersonaCoreResponse persona, PropuestaGuaraniResponse propuesta) {
        return CreatePersonalesResponse.builder()
                .result(true)
                .alumnoGuarani(alumno())
                .persona(persona)
                .propuestaGuarani(propuesta)
                .build();
    }

    private AlumnoGuarani alumno() {
        return AlumnoGuarani.builder().alumno(1).persona(4500).propuesta(3).build();
    }

    private PropuestaGuaraniResponse propuesta(List<PropuestaResponsableAcademicaGuaraniResponse> responsables) {
        return PropuestaGuaraniResponse.builder().propuesta(3).responsablesAcademicas(responsables).build();
    }
}
