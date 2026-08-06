package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;

public interface CreatePersonalesPort {

    Boolean createPersonales(AlumnoGuarani alumno);
}
