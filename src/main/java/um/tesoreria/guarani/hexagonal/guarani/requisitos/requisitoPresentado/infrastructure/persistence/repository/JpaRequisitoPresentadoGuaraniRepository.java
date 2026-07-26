package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.infrastructure.persistence.entity.RequisitoPresentadoGuaraniEntity;

@Repository
public interface JpaRequisitoPresentadoGuaraniRepository extends JpaRepository<RequisitoPresentadoGuaraniEntity, Integer> {
}
