package um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;

public interface GetAlumnoGuaraniUseCase {
    AlumnoGuarani getByAlumnoId(Integer alumnoId);
}
