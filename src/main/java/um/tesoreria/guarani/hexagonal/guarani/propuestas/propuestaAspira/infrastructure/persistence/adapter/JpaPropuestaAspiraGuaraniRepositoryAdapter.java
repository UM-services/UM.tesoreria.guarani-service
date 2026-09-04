package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.out.PropuestaAspiraGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.mapper.PropuestaAspiraGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.repository.JpaPropuestaAspiraGuaraniRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaPropuestaAspiraGuaraniRepositoryAdapter implements PropuestaAspiraGuaraniRepository {

    private final JpaPropuestaAspiraGuaraniRepository jpaPropuestaAspiraGuaraniRepository;
    private final PropuestaAspiraGuaraniMapper mapper;

    @Override
    public Optional<PropuestaAspiraGuarani> findByPropuestaAspiraId(Integer propuestaAspiraId) {
        return jpaPropuestaAspiraGuaraniRepository.findById(propuestaAspiraId)
                .map(mapper::toDomain);
    }

    @Override
    public List<PropuestaAspiraGuarani> findAllByPropuestaAndUbicacionAndFechaInscripcionFromAndAnioAcademico(
            Integer propuesta, Integer ubicacion, LocalDate fechaDesde, Integer anioAcademico) {
        return jpaPropuestaAspiraGuaraniRepository
                .findAllByPropuestaAndUbicacionAndFechaInscripcionGreaterThanEqualAndAnioAcademico(
                        propuesta, ubicacion, fechaDesde, toAnioAcademico(anioAcademico))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    private static BigDecimal toAnioAcademico(Integer anioAcademico) {
        return Objects.isNull(anioAcademico) ? null : BigDecimal.valueOf(anioAcademico);
    }

}
