package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model.UbicacionTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.ports.out.UbicacionTipoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.mapper.UbicacionTipoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.repository.JpaUbicacionTipoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaUbicacionTipoGuaraniRepositoryAdapter implements UbicacionTipoGuaraniRepository {

    private final JpaUbicacionTipoGuaraniRepository jpaRepository;
    private final UbicacionTipoGuaraniMapper mapper;

    @Override
    public List<UbicacionTipoGuarani> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
