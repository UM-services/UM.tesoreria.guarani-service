package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in.GetPropuestaAspiraGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in.GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropuestaAspiraGuaraniServiceTest {

    private static final Integer PROPUESTA = 12;
    private static final Integer UBICACION = 3;
    private static final LocalDate FECHA_DESDE = LocalDate.of(2026, 3, 1);
    private static final Integer ANIO_ACADEMICO = 2026;

    @Mock
    private GetPropuestaAspiraGuaraniUseCase getPropuestaAspiraGuaraniUseCase;

    @Mock
    private GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase getPropuestaAspirasUseCase;

    @InjectMocks
    private PropuestaAspiraGuaraniService service;

    @Test
    void forwardsCriteriaToUseCaseKeepingArgumentOrder() {
        List<PropuestaAspiraGuarani> expected = List.of(PropuestaAspiraGuarani.builder().propuestaAspira(1).build());
        when(getPropuestaAspirasUseCase.getByPropuestaUbicacionFechaInscripcionAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, ANIO_ACADEMICO)).thenReturn(expected);

        List<PropuestaAspiraGuarani> result = service.getByPropuestaUbicacionFechaInscripcionAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, ANIO_ACADEMICO);

        assertThat(result).isSameAs(expected);
        verify(getPropuestaAspirasUseCase).getByPropuestaUbicacionFechaInscripcionAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, ANIO_ACADEMICO);
    }
}
