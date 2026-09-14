package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.CreatePersonalesPort;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.PersonaCoreResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.application.service.PropuestaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.mapper.PropuestaGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.dto.PropuestaResponsableAcademicaGuaraniResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePersonalesByNroDocumentoUseCaseImplTest {

    private static final Integer PROPUESTA = 3;
    private static final String DOCUMENTO = "AA1234567";

    @Mock
    private GetAlumnosByNroDocumentoUseCase getAlumnosByNroDocumentoUseCase;

    @Mock
    private CreatePersonalesPort createPersonalesPort;

    @Mock
    private PropuestaGuaraniService propuestaGuaraniService;

    @Mock
    private PropuestaGuaraniDtoMapper propuestaGuaraniDtoMapper;

    @InjectMocks
    private CreatePersonalesByNroDocumentoUseCaseImpl useCase;

    @Test
    void enrichesOnlyTheResponsesCoreCreated() {
        var ok = alumno(1);
        var fallado = alumno(2);
        when(getAlumnosByNroDocumentoUseCase.getByNroDocumento(DOCUMENTO)).thenReturn(List.of(ok, fallado));
        when(createPersonalesPort.createPersonales(ok)).thenReturn(creado());
        when(createPersonalesPort.createPersonales(fallado)).thenReturn(noCreado());
        when(propuestaGuaraniService.getByPropuestaId(PROPUESTA)).thenReturn(PropuestaGuarani.builder().propuesta(PROPUESTA).build());
        when(propuestaGuaraniDtoMapper.toResponse(any(PropuestaGuarani.class))).thenReturn(propuestaConResponsable());

        List<CreatePersonalesResponse> responses = useCase.createPersonalesByNroDocumento(DOCUMENTO);

        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getPropuestaGuarani()).isNotNull();
        assertThat(responses.get(1).getPropuestaGuarani()).isNull();
        verify(propuestaGuaraniService, times(1)).getByPropuestaId(PROPUESTA);
    }

    @Test
    void keepsResponsesWithoutPersonasSoTheCallerCanReportThem() {
        var alumno = alumno(1);
        when(getAlumnosByNroDocumentoUseCase.getByNroDocumento(DOCUMENTO)).thenReturn(List.of(alumno));
        when(createPersonalesPort.createPersonales(alumno)).thenReturn(noCreado());

        assertThat(useCase.createPersonalesByNroDocumento(DOCUMENTO)).singleElement().satisfies(response -> {
            assertThat(response.esCreado()).isFalse();
            assertThat(response.getPersona()).isNull();
        });

        verifyNoInteractionsDePropuesta();
    }

    @Test
    void returnsTheCorePersonaSoTheCallerCanReadPrefijoAndPosfijo() {
        var alumno = alumno(1);
        var respuesta = creado();
        respuesta.getPersona().setNumeroPrefijo("AA");
        respuesta.getPersona().setNumeroPosfijo("");
        when(getAlumnosByNroDocumentoUseCase.getByNroDocumento(DOCUMENTO)).thenReturn(List.of(alumno));
        when(createPersonalesPort.createPersonales(alumno)).thenReturn(respuesta);
        when(propuestaGuaraniService.getByPropuestaId(PROPUESTA)).thenReturn(PropuestaGuarani.builder().propuesta(PROPUESTA).build());
        when(propuestaGuaraniDtoMapper.toResponse(any(PropuestaGuarani.class))).thenReturn(propuestaConResponsable());

        List<CreatePersonalesResponse> responses = useCase.createPersonalesByNroDocumento(DOCUMENTO);

        assertThat(responses.get(0).getPersona().getNumeroPrefijo()).isEqualTo("AA");
        assertThat(responses.get(0).getPersona().getUniqueId()).isEqualTo(99L);
    }

    private void verifyNoInteractionsDePropuesta() {
        verify(propuestaGuaraniService, times(0)).getByPropuestaId(any());
        verify(propuestaGuaraniDtoMapper, times(0)).toResponse(any());
    }

    private AlumnoGuarani alumno(int id) {
        return AlumnoGuarani.builder()
                .alumno(id)
                .persona(4500 + id)
                .propuesta(PROPUESTA)
                .personaRel(PersonaGuarani.builder().persona(4500 + id).numeroPrefijo("AA").numeroPosfijo("").build())
                .build();
    }

    private CreatePersonalesResponse creado() {
        return CreatePersonalesResponse.builder()
                .result(true)
                .alumnoGuarani(alumno(1))
                .persona(PersonaCoreResponse.builder()
                        .uniqueId(99L)
                        .documentoId(1)
                        .numeroPrefijo("AA")
                        .numeroPosfijo("")
                        .guaraniPersona(4501L)
                        .build())
                .build();
    }

    private CreatePersonalesResponse noCreado() {
        return CreatePersonalesResponse.builder().result(false).alumnoGuarani(alumno(2)).build();
    }

    private PropuestaGuaraniResponse propuestaConResponsable() {
        return PropuestaGuaraniResponse.builder()
                .propuesta(PROPUESTA)
                .responsablesAcademicas(List.of(PropuestaResponsableAcademicaGuaraniResponse.builder().propuesta(PROPUESTA).responsableAcademica(5).build()))
                .build();
    }
}
