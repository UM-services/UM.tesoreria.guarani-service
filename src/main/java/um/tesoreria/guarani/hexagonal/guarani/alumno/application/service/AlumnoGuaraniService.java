package um.tesoreria.guarani.hexagonal.guarani.alumno.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.GetAlumnoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.GetAlumnosByPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.ProcessNextPreuniversitarioUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoGuaraniService {

    private final GetAlumnoGuaraniUseCase getAlumnoGuaraniUseCase;
    private final GetAlumnosByPropuestaTipoUseCase getAlumnosByPropuestaTipoUseCase;
    private final ProcessNextPreuniversitarioUseCase processNextPreuniversitarioUseCase;

    public AlumnoGuarani getByAlumnoId(Integer alumnoId) {
        return getAlumnoGuaraniUseCase.getByAlumnoId(alumnoId);
    }

    public List<AlumnoGuarani> getAllByPropuestaTipo(Integer propuestaTipo) {
        return getAlumnosByPropuestaTipoUseCase.getByPropuestaTipo(propuestaTipo);
    }

    public void processNextInscripcion() {
        processNextPreuniversitarioUseCase.processNextPreuniversitario();
    }

}
