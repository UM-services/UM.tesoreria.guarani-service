package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePreuniversitarioUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.ProcessNextPreuniversitarioUseCase;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProcessNextPreuniversitarioUseCaseImpl implements ProcessNextPreuniversitarioUseCase {

    private static final int PROPUESTA_TIPO_PREUNIVERSITARIO = 204;
    private static final int DIAS_LIMITE = 15;

    private final GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase getAlumnosByPropuestaTipoAndFechaInscripcionUseCase;
    private final CreatePreuniversitarioUseCase createPreuniversitarioUseCase;

    @Override
    public void processNextPreuniversitario() {
        log.debug("\n\nProcessing ProcessNextPreuniversitarioUseCaseImpl.processNextPreuniversitario\n\n");
        var fechaLimite = LocalDate.now().minusDays(DIAS_LIMITE);
        List<AlumnoGuarani> alumnos = getAlumnosByPropuestaTipoAndFechaInscripcionUseCase
                .getByPropuestaTipoAndFechaInscripcionAfter(PROPUESTA_TIPO_PREUNIVERSITARIO, fechaLimite);
        createPreuniversitarioUseCase.createPreuniversitario(alumnos);
    }

}
