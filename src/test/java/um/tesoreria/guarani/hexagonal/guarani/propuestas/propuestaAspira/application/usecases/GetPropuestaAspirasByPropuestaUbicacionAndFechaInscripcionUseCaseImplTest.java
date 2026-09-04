package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.out.PropuestaAspiraGuaraniRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCaseImplTest {

    private static final Integer PROPUESTA = 12;
    private static final Integer UBICACION = 3;
    private static final LocalDate FECHA_DESDE = LocalDate.of(2026, 3, 1);
    private static final Integer ANIO_ACADEMICO = 2026;

    @Mock
    private PropuestaAspiraGuaraniRepository repository;

    @InjectMocks
    private GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCaseImpl useCase;

    @Test
    void delegatesAllCriteriaIncludingAnioAcademicoToRepository() {
        List<PropuestaAspiraGuarani> expected = List.of(PropuestaAspiraGuarani.builder()
                .propuestaAspira(1)
                .propuesta(PROPUESTA)
                .ubicacion(UBICACION)
                .fechaInscripcion(FECHA_DESDE)
                .anioAcademico(BigDecimal.valueOf(ANIO_ACADEMICO))
                .build());
        when(repository.findAllByPropuestaAndUbicacionAndFechaInscripcionFromAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, ANIO_ACADEMICO)).thenReturn(expected);

        List<PropuestaAspiraGuarani> result = useCase.getByPropuestaUbicacionFechaInscripcionAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, ANIO_ACADEMICO);

        assertThat(result).isEqualTo(expected);
        verify(repository).findAllByPropuestaAndUbicacionAndFechaInscripcionFromAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, ANIO_ACADEMICO);
    }

    @Test
    void returnsEmptyListWhenRepositoryHasNoMatches() {
        when(repository.findAllByPropuestaAndUbicacionAndFechaInscripcionFromAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, ANIO_ACADEMICO)).thenReturn(List.of());

        assertThat(useCase.getByPropuestaUbicacionFechaInscripcionAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, ANIO_ACADEMICO)).isEmpty();
    }
}
