package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.AlumnoGuaraniRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAlumnosByPropuestaTipoAndFechaInscripcionUseCaseImpl implements GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase {

    private final AlumnoGuaraniRepository repository;

    @Override
    public List<AlumnoGuarani> getByPropuestaTipoAndFechaInscripcionAfter(Integer propuestaTipo, LocalDate fechaLimite) {
        return repository.findAllByPropuestaTipoAndFechaInscripcionAfter(propuestaTipo, fechaLimite);
    }

}
