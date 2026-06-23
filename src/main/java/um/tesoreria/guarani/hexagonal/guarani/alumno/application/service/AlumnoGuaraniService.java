package um.tesoreria.guarani.hexagonal.guarani.alumno.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.CheckAllToUnmarkSendedUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.GetAlumnoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.GetAlumnosByPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.ProcessNextPreuniversitarioUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.dto.AlumnoDeteccionRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoGuaraniService {

    private final GetAlumnoGuaraniUseCase getAlumnoGuaraniUseCase;
    private final GetAlumnosByPropuestaTipoUseCase getAlumnosByPropuestaTipoUseCase;
    private final ProcessNextPreuniversitarioUseCase processNextPreuniversitarioUseCase;
    private final CheckAllToUnmarkSendedUseCase checkAllToUnmarkSendedUseCase;

    public AlumnoGuarani getByAlumnoId(Integer alumnoId) {
        return getAlumnoGuaraniUseCase.getByAlumnoId(alumnoId);
    }

    public List<AlumnoGuarani> getAllByPropuestaTipo(Integer propuestaTipo) {
        return getAlumnosByPropuestaTipoUseCase.getByPropuestaTipo(propuestaTipo);
    }

    public void processNextInscripcion() {
        processNextPreuniversitarioUseCase.processNextPreuniversitario();
    }

    public List<AlumnoDeteccionRequest> checkAllAlumnosWithoutChequera(List<AlumnoDeteccionRequest> alumnos) {
        return checkAllToUnmarkSendedUseCase.checkAllAlumnosWithoutChequera(alumnos);
    }

}
