package um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.ports.out.PropuestaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.mapper.PropuestaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.repository.JpaPropuestaGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaPropuestaGuaraniRepositoryAdapter implements PropuestaGuaraniRepository {

    private final JpaPropuestaGuaraniRepository jpaPropuestaGuaraniRepository;
    private final PropuestaGuaraniMapper mapper;

    @Override
    public Optional<PropuestaGuarani> findByPropuestaId(Integer propuestaId) {
        return jpaPropuestaGuaraniRepository.findById(propuestaId)
                .map(mapper::toDomain);
    }

    @Override
    public List<PropuestaGuarani> findAll() {
        return jpaPropuestaGuaraniRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
