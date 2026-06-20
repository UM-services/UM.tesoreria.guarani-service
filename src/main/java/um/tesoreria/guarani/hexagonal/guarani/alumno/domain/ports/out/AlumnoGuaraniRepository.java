package um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import java.util.List;
import java.util.Optional;

public interface AlumnoGuaraniRepository {
    Optional<AlumnoGuarani> findByAlumnoId(Integer alumnoId);
    List<AlumnoGuarani> findAllByPropuestaTipo(Integer propuestaTipo);
}
