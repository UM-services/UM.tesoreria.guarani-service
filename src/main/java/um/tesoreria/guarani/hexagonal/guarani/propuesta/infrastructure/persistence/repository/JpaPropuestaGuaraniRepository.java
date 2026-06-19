package um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;

@Repository
public interface JpaPropuestaGuaraniRepository extends JpaRepository<PropuestaGuaraniEntity, Integer> {
}
