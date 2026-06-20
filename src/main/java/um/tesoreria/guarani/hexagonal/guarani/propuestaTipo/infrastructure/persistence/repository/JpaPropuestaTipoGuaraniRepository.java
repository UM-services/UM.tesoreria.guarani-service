package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.persistence.entity.PropuestaTipoGuaraniEntity;

@Repository
public interface JpaPropuestaTipoGuaraniRepository extends JpaRepository<PropuestaTipoGuaraniEntity, Integer> {
}
