package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.model.RequisitoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.out.RequisitoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.persistence.mapper.RequisitoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.persistence.repository.JpaRequisitoGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRequisitoGuaraniRepositoryAdapter implements RequisitoGuaraniRepository {

    private final JpaRequisitoGuaraniRepository jpaRequisitoGuaraniRepository;
    private final RequisitoGuaraniMapper mapper;

    @Override
    public Optional<RequisitoGuarani> findByRequisitoId(Integer requisitoId) {
        return jpaRequisitoGuaraniRepository.findById(requisitoId)
                .map(mapper::toDomain);
    }

    @Override
    public List<RequisitoGuarani> findAll() {
        return jpaRequisitoGuaraniRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
