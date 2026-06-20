package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;

import java.util.List;

@Repository
public interface JpaAlumnoGuaraniRepository extends JpaRepository<AlumnoGuaraniEntity, Integer> {

    @Query("SELECT a FROM AlumnoGuaraniEntity a JOIN a.propuestaRel p WHERE p.propuestaTipo = :propuestaTipo")
    List<AlumnoGuaraniEntity> findAllByPropuestaTipo(@Param("propuestaTipo") Integer propuestaTipo);

}
