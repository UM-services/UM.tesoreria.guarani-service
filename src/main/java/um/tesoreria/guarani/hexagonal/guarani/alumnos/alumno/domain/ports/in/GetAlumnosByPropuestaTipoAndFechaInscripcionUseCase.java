package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;

import java.time.LocalDate;
import java.util.List;

public interface GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase {

    List<AlumnoGuarani> getByPropuestaTipoAndFechaInscripcionAfter(Integer propuestaTipo, LocalDate fechaLimite);

}
