package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.entity.RequisitoTipoGuaraniEntity;

@Repository
public interface JpaRequisitoTipoGuaraniRepository extends JpaRepository<RequisitoTipoGuaraniEntity, Integer> {
}
