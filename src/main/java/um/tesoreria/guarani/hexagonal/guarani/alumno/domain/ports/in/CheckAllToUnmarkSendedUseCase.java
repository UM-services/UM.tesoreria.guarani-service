package um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.dto.AlumnoDeteccionRequest;

import java.util.List;

public interface CheckAllToUnmarkSendedUseCase {

    List<AlumnoDeteccionRequest> checkAllAlumnosWithoutChequera(List<AlumnoDeteccionRequest> alumnos);

}
