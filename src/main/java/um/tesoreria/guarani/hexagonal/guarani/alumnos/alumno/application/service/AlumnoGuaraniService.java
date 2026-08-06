package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePreuniversitarioByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePersonalesByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.ProcessNextPreuniversitarioUseCase;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoGuaraniService {

    private final GetAlumnoGuaraniUseCase getAlumnoGuaraniUseCase;
    private final GetAlumnosByPropuestaTipoUseCase getAlumnosByPropuestaTipoUseCase;
    private final GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase getAlumnosByPropuestaTipoAndFechaInscripcionUseCase;
    private final ProcessNextPreuniversitarioUseCase processNextPreuniversitarioUseCase;
    private final GetAlumnosByNroDocumentoUseCase getAlumnosByNroDocumentoUseCase;
    private final CreatePreuniversitarioByNroDocumentoUseCase createPreuniversitarioByNroDocumentoUseCase;
    private final CreatePersonalesByNroDocumentoUseCase createPersonalesByNroDocumentoUseCase;

    public AlumnoGuarani getByAlumnoId(Integer alumnoId) {
        return getAlumnoGuaraniUseCase.getByAlumnoId(alumnoId);
    }

    public List<AlumnoGuarani> getAllByPropuestaTipo(Integer propuestaTipo) {
        return getAlumnosByPropuestaTipoUseCase.getByPropuestaTipo(propuestaTipo);
    }

    public List<AlumnoGuarani> getAllByPropuestaTipoAndFechaLimite(Integer propuestaTipo, LocalDate fechaLimite) {
        return getAlumnosByPropuestaTipoAndFechaInscripcionUseCase.getByPropuestaTipoAndFechaInscripcionAfter(propuestaTipo, fechaLimite);
    }

    public List<AlumnoGuarani> getAllByNroDocumento(String nroDocumento) {
        return getAlumnosByNroDocumentoUseCase.getByNroDocumento(nroDocumento);
    }

    public void processNextInscripcion() {
        processNextPreuniversitarioUseCase.processNextPreuniversitario();
    }

    public List<AlumnoGuarani> createPreuniversitarioByNroDocumento(String nroDocumento) {
        return createPreuniversitarioByNroDocumentoUseCase.createPreuniversitarioByNroDocumento(nroDocumento);
    }

    public Boolean createPersonalesByNroDocumento(String nroDocumento) {
        return createPersonalesByNroDocumentoUseCase.createPersonalesByNroDocumento(nroDocumento);
    }

}
