package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.out.RequisitoTipoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.mapper.RequisitoTipoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.repository.JpaRequisitoTipoGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRequisitoTipoGuaraniRepositoryAdapter implements RequisitoTipoGuaraniRepository {

    private final JpaRequisitoTipoGuaraniRepository jpaRequisitoTipoGuaraniRepository;
    private final RequisitoTipoGuaraniMapper mapper;

    @Override
    public Optional<RequisitoTipoGuarani> findByRequisitoTipo(Integer requisitoTipo) {
        return jpaRequisitoTipoGuaraniRepository.findById(requisitoTipo)
                .map(mapper::toDomain);
    }

    @Override
    public List<RequisitoTipoGuarani> findAll() {
        return jpaRequisitoTipoGuaraniRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
