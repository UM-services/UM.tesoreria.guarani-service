package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;

public interface CreatePersonalesPort {

    CreatePersonalesResponse createPersonales(AlumnoGuarani alumno);
}
