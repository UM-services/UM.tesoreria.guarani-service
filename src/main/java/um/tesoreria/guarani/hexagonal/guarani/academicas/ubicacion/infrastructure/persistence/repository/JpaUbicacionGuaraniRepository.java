package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.infrastructure.persistence.entity.UbicacionGuaraniEntity;

import java.util.List;

@Repository
public interface JpaUbicacionGuaraniRepository extends JpaRepository<UbicacionGuaraniEntity, Integer> {
    List<UbicacionGuaraniEntity> findAllByUbicacionTipo(Integer ubicacionTipo);
}
