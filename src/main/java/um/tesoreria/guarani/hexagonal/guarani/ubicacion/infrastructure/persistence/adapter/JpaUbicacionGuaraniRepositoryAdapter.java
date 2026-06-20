package um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.ports.out.UbicacionGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.mapper.UbicacionGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.repository.JpaUbicacionGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaUbicacionGuaraniRepositoryAdapter implements UbicacionGuaraniRepository {

    private final JpaUbicacionGuaraniRepository jpaUbicacionGuaraniRepository;
    private final UbicacionGuaraniMapper mapper;

    @Override
    public Optional<UbicacionGuarani> findByUbicacionId(Integer ubicacionId) {
        return jpaUbicacionGuaraniRepository.findById(ubicacionId)
                .map(mapper::toDomain);
    }

    @Override
    public List<UbicacionGuarani> findAll() {
        return jpaUbicacionGuaraniRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
