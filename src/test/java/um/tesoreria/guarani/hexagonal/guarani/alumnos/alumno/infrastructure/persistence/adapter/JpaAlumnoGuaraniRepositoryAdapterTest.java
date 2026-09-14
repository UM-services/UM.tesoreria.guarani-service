package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.mapper.AlumnoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.repository.JpaAlumnoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.entity.PersonaDocumentoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.repository.JpaPersonaDocumentoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.entity.PersonaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.mapper.PersonaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.repository.JpaPersonaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.repository.JpaPropuestaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.repository.JpaPropuestaAspiraGuaraniRepository;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaAlumnoGuaraniRepositoryAdapterTest {

    private static final Integer PERSONA = 4500;
    private static final String DOCUMENTO_CON_LETRAS = "AA1234567";
    private static final String DIGITOS = "1234567";

    @Mock
    private JpaAlumnoGuaraniRepository jpaAlumnoGuaraniRepository;

    @Mock
    private JpaPropuestaGuaraniRepository jpaPropuestaGuaraniRepository;

    @Mock
    private JpaPropuestaAspiraGuaraniRepository jpaPropuestaAspiraGuaraniRepository;

    @Mock
    private JpaPersonaDocumentoGuaraniRepository jpaPersonaDocumentoGuaraniRepository;

    @Mock
    private JpaPersonaGuaraniRepository jpaPersonaGuaraniRepository;

    @Mock
    private PersonaGuaraniMapper personaGuaraniMapper;

    @Mock
    private AlumnoGuaraniMapper mapper;

    @InjectMocks
    private JpaAlumnoGuaraniRepositoryAdapter adapter;

    @Test
    void usesTheExactMatchWhenTheDocumentIsStoredAsProvided() {
        when(jpaPersonaDocumentoGuaraniRepository.findAllByNroDocumento(DOCUMENTO_CON_LETRAS))
                .thenReturn(List.of(documento()));
        when(jpaAlumnoGuaraniRepository.findAllByPersonaIn(anyCollection())).thenReturn(List.of(alumno()));
        when(mapper.toDomain(any(AlumnoGuaraniEntity.class))).thenReturn(AlumnoGuarani.builder().alumno(1).persona(PERSONA).build());

        List<AlumnoGuarani> result = adapter.findAllByNroDocumento(DOCUMENTO_CON_LETRAS);

        assertThat(result).hasSize(1);
        assertThat(personasConsultadas()).containsExactly(PERSONA);
        verify(jpaPersonaDocumentoGuaraniRepository, never()).findAllByDigitosNroDocumento(any());
    }

    @Test
    void fallsBackToTheNumericPartWhenTheExactMatchFails() {
        when(jpaPersonaDocumentoGuaraniRepository.findAllByNroDocumento(DIGITOS)).thenReturn(List.of());
        when(jpaPersonaDocumentoGuaraniRepository.findAllByDigitosNroDocumento(DIGITOS)).thenReturn(List.of(documento()));
        when(jpaAlumnoGuaraniRepository.findAllByPersonaIn(anyCollection())).thenReturn(List.of(alumno()));
        when(mapper.toDomain(any(AlumnoGuaraniEntity.class))).thenReturn(AlumnoGuarani.builder().alumno(1).persona(PERSONA).build());

        List<AlumnoGuarani> result = adapter.findAllByNroDocumento(DIGITOS);

        assertThat(result).hasSize(1);
        verify(jpaPersonaDocumentoGuaraniRepository).findAllByDigitosNroDocumento(DIGITOS);
    }

    @Test
    void skipsTheNumericSearchWhenTheProvidedValueHasNoDigits() {
        when(jpaPersonaDocumentoGuaraniRepository.findAllByNroDocumento("ABC")).thenReturn(List.of());

        assertThat(adapter.findAllByNroDocumento("ABC")).isEmpty();

        verify(jpaPersonaDocumentoGuaraniRepository, never()).findAllByDigitosNroDocumento(any());
        verifyNoInteractions(jpaAlumnoGuaraniRepository);
    }

    @Test
    void doesNotQueryAlumnosWithoutDocuments() {
        when(jpaPersonaDocumentoGuaraniRepository.findAllByNroDocumento(DIGITOS)).thenReturn(List.of());
        when(jpaPersonaDocumentoGuaraniRepository.findAllByDigitosNroDocumento(DIGITOS)).thenReturn(List.of());

        assertThat(adapter.findAllByNroDocumento(DIGITOS)).isEmpty();

        verifyNoInteractions(jpaAlumnoGuaraniRepository);
    }

    @Test
    void fallsBackToPersonaWhenNoAlumnosFoundForPersona() {
        when(jpaPersonaDocumentoGuaraniRepository.findAllByNroDocumento(DOCUMENTO_CON_LETRAS))
                .thenReturn(List.of(documento()));
        when(jpaAlumnoGuaraniRepository.findAllByPersonaIn(anyCollection())).thenReturn(List.of());
        var personaEntity = PersonaGuaraniEntity.builder().persona(PERSONA).apellido("Perez").build();
        when(jpaPersonaGuaraniRepository.findAllById(anyCollection())).thenReturn(List.of(personaEntity));
        when(personaGuaraniMapper.toDomain(personaEntity))
                .thenReturn(PersonaGuarani.builder().persona(PERSONA).apellido("Perez").build());

        List<AlumnoGuarani> result = adapter.findAllByNroDocumento(DOCUMENTO_CON_LETRAS);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getPersona()).isEqualTo(PERSONA);
        assertThat(result.get(0).getPersonaRel()).isNotNull();
        assertThat(result.get(0).getPersonaRel().getApellido()).isEqualTo("Perez");
    }

    private Collection<Integer> personasConsultadas() {
        ArgumentCaptor<Collection<Integer>> captor = ArgumentCaptor.forClass(Collection.class);
        verify(jpaAlumnoGuaraniRepository).findAllByPersonaIn(captor.capture());
        return captor.getValue();
    }

    private PersonaDocumentoGuaraniEntity documento() {
        return PersonaDocumentoGuaraniEntity.builder()
                .documento(10)
                .persona(PERSONA)
                .nroDocumento(DOCUMENTO_CON_LETRAS)
                .build();
    }

    private AlumnoGuaraniEntity alumno() {
        return AlumnoGuaraniEntity.builder()
                .alumno(1)
                .persona(PERSONA)
                .build();
    }
}
