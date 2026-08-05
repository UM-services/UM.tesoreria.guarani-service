package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface JpaAlumnoGuaraniRepository extends JpaRepository<AlumnoGuaraniEntity, Integer> {

    @EntityGraph(attributePaths = {"personaRel", "personaRel.documentoPrincipalRel"})
    List<AlumnoGuaraniEntity> findAllByPersonaIn(Collection<Integer> personas);
}
