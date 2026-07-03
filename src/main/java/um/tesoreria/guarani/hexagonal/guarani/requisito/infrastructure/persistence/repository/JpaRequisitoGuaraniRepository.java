package um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.persistence.entity.RequisitoGuaraniEntity;

@Repository
public interface JpaRequisitoGuaraniRepository extends JpaRepository<RequisitoGuaraniEntity, Integer> {
}
