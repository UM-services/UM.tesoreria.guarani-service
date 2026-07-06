package um.tesoreria.guarani.hexagonal.guarani.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.CreatePreuniversitarioUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.GetAlumnosByPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.ProcessNextPreuniversitarioUseCase;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProcessNextPreuniversitarioUseCaseImpl implements ProcessNextPreuniversitarioUseCase {

    private static final int PROPUESTA_TIPO_PREUNIVERSITARIO = 204;

    private final GetAlumnosByPropuestaTipoUseCase getAlumnosByPropuestaTipoUseCase;
    private final CreatePreuniversitarioUseCase createPreuniversitarioUseCase;

    @Override
    public void processNextPreuniversitario() {
        log.debug("\n\nProcessing ProcessNextPreuniversitarioUseCaseImpl.processNextPreuniversitario\n\n");
        List<AlumnoGuarani> alumnos = getAlumnosByPropuestaTipoUseCase.getByPropuestaTipo(PROPUESTA_TIPO_PREUNIVERSITARIO);
        createPreuniversitarioUseCase.createPreuniversitario(alumnos);
    }

}
