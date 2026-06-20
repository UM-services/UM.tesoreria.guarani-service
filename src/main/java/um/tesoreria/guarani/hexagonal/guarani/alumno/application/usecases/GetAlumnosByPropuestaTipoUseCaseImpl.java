package um.tesoreria.guarani.hexagonal.guarani.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.GetAlumnosByPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.out.AlumnoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAlumnosByPropuestaTipoUseCaseImpl implements GetAlumnosByPropuestaTipoUseCase {

    private final AlumnoGuaraniRepository repository;

    @Override
    public List<AlumnoGuarani> getByPropuestaTipo(Integer propuestaTipo) {
        return repository.findAllByPropuestaTipo(propuestaTipo);
    }
}
