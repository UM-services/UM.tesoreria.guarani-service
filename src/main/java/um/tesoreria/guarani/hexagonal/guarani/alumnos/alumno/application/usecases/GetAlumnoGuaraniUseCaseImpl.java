package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.exception.AlumnoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.AlumnoGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetAlumnoGuaraniUseCaseImpl implements GetAlumnoGuaraniUseCase {

    private final AlumnoGuaraniRepository repository;

    @Override
    public AlumnoGuarani getByAlumnoId(Integer alumnoId) {
        return repository.findByAlumnoId(alumnoId).orElseThrow(() -> new AlumnoGuaraniException(alumnoId));
    }
}
