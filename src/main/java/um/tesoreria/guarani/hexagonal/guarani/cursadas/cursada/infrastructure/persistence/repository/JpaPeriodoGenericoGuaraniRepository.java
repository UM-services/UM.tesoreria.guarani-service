package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.PeriodoGenericoGuaraniEntity;

public interface JpaPeriodoGenericoGuaraniRepository extends JpaRepository<PeriodoGenericoGuaraniEntity, Integer> {
}
