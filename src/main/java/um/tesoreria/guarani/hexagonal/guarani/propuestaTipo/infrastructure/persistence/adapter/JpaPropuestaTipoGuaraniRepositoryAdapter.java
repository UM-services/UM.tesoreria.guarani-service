package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.out.PropuestaTipoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.persistence.mapper.PropuestaTipoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.persistence.repository.JpaPropuestaTipoGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaPropuestaTipoGuaraniRepositoryAdapter implements PropuestaTipoGuaraniRepository {

    private final JpaPropuestaTipoGuaraniRepository jpaPropuestaTipoGuaraniRepository;
    private final PropuestaTipoGuaraniMapper mapper;

    @Override
    public Optional<PropuestaTipoGuarani> findByPropuestaTipo(Integer propuestaTipo) {
        return jpaPropuestaTipoGuaraniRepository.findById(propuestaTipo)
                .map(mapper::toDomain);
    }

    @Override
    public List<PropuestaTipoGuarani> findAll() {
        return jpaPropuestaTipoGuaraniRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
