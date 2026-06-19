package um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.entity.UbicacionGuaraniEntity;

@Repository
public interface JpaUbicacionGuaraniRepository extends JpaRepository<UbicacionGuaraniEntity, Integer> {
}
