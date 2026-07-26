package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.out.PropuestaAspiraGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.mapper.PropuestaAspiraGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.repository.JpaPropuestaAspiraGuaraniRepository;

import java.util.List;
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
    public List<PropuestaAspiraGuarani> findAll() {
        return jpaPropuestaAspiraGuaraniRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
