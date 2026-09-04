package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.entity.PropuestaAspiraGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.mapper.PropuestaAspiraGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.repository.JpaPropuestaAspiraGuaraniRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaPropuestaAspiraGuaraniRepositoryAdapterTest {

    private static final Integer PROPUESTA = 12;
    private static final Integer UBICACION = 3;
    private static final LocalDate FECHA_DESDE = LocalDate.of(2026, 3, 1);

    @Mock
    private JpaPropuestaAspiraGuaraniRepository jpaPropuestaAspiraGuaraniRepository;

    @Mock
    private PropuestaAspiraGuaraniMapper mapper;

    @InjectMocks
    private JpaPropuestaAspiraGuaraniRepositoryAdapter adapter;

    @Test
    void convertsAnioAcademicoToBigDecimalAndMapsResults() {
        PropuestaAspiraGuaraniEntity entity = PropuestaAspiraGuaraniEntity.builder()
                .propuestaAspira(1)
                .anioAcademico(new BigDecimal("2026.00"))
                .build();
        PropuestaAspiraGuarani domain = PropuestaAspiraGuarani.builder().propuestaAspira(1).build();
        when(jpaPropuestaAspiraGuaraniRepository
                .findAllByPropuestaAndUbicacionAndFechaInscripcionGreaterThanEqualAndAnioAcademico(
                        eq(PROPUESTA), eq(UBICACION), eq(FECHA_DESDE), any(BigDecimal.class)))
                .thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<PropuestaAspiraGuarani> result = adapter.findAllByPropuestaAndUbicacionAndFechaInscripcionFromAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, 2026);

        ArgumentCaptor<BigDecimal> captor = ArgumentCaptor.forClass(BigDecimal.class);
        verify(jpaPropuestaAspiraGuaraniRepository)
                .findAllByPropuestaAndUbicacionAndFechaInscripcionGreaterThanEqualAndAnioAcademico(
                        eq(PROPUESTA), eq(UBICACION), eq(FECHA_DESDE), captor.capture());
        assertThat(captor.getValue()).isEqualByComparingTo("2026");
        assertThat(result).containsExactly(domain);
    }

    @Test
    void propagatesNullAnioAcademicoWithoutFailing() {
        when(jpaPropuestaAspiraGuaraniRepository
                .findAllByPropuestaAndUbicacionAndFechaInscripcionGreaterThanEqualAndAnioAcademico(
                        eq(PROPUESTA), eq(UBICACION), eq(FECHA_DESDE), isNull()))
                .thenReturn(List.of());

        assertThat(adapter.findAllByPropuestaAndUbicacionAndFechaInscripcionFromAndAnioAcademico(
                PROPUESTA, UBICACION, FECHA_DESDE, null)).isEmpty();
    }
}
