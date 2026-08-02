package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.entity.UbicacionTipoGuaraniEntity;

@Repository
public interface JpaUbicacionTipoGuaraniRepository
        extends JpaRepository<UbicacionTipoGuaraniEntity, Integer> {
}
