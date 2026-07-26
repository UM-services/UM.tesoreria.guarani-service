package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JpaAlumnoGuaraniByFechaRepository extends JpaRepository<AlumnoGuaraniEntity, Integer> {

    List<AlumnoGuaraniEntity> findByPropuestaRel_PropuestaTipoAndPropuestaAspiraRel_FechaInscripcionAfter(
            Integer propuestaTipo, LocalDate fechaLimite);

}
