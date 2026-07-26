package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AlumnoGuaraniRepository {
    Optional<AlumnoGuarani> findByAlumnoId(Integer alumnoId);
    List<AlumnoGuarani> findAllByPropuestaTipo(Integer propuestaTipo);
    List<AlumnoGuarani> findAllByPropuestaTipoAndFechaInscripcionAfter(Integer propuestaTipo, LocalDate fechaLimite);
    List<AlumnoGuarani> findAllByNroDocumento(String nroDocumento);
}
