package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;

public interface GetAlumnoGuaraniUseCase {
    AlumnoGuarani getByAlumnoId(Integer alumnoId);
}
