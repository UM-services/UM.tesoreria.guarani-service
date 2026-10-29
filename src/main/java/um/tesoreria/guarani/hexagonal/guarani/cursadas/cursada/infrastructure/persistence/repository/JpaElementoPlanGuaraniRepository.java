package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.ElementoPlanGuaraniEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface JpaElementoPlanGuaraniRepository extends JpaRepository<ElementoPlanGuaraniEntity, Integer> {

    @EntityGraph(attributePaths = {"elementoRevisionRel"})
    List<ElementoPlanGuaraniEntity> findAllByPlanVersionInAndElementoRevisionRel_ElementoIn(
            Collection<Integer> planVersiones, Collection<Integer> elementos);
}
