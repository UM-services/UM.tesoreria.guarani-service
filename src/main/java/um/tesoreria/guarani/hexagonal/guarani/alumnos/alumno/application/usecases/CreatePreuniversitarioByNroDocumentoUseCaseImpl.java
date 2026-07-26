package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePreuniversitarioByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePreuniversitarioUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePreuniversitarioByNroDocumentoUseCaseImpl implements CreatePreuniversitarioByNroDocumentoUseCase {

    private static final int PROPUESTA_TIPO_PREUNIVERSITARIO = 204;

    private final GetAlumnosByNroDocumentoUseCase getAlumnosByNroDocumentoUseCase;
    private final CreatePreuniversitarioUseCase createPreuniversitarioUseCase;

    @Override
    public List<AlumnoGuarani> createPreuniversitarioByNroDocumento(String nroDocumento) {
        log.debug("\n\nCreating preuniversitario for document: {}\n\n", nroDocumento);
        List<AlumnoGuarani> alumnos = getAlumnosByNroDocumentoUseCase.getByNroDocumento(nroDocumento);
        
        // Filter only pre-university students (propuestaTipo == 204)
        List<AlumnoGuarani> preuniversitarioAlumnos = alumnos.stream()
                .filter(alumno -> alumno.getPropuestaRel() != null 
                        && Integer.valueOf(PROPUESTA_TIPO_PREUNIVERSITARIO).equals(alumno.getPropuestaRel().getPropuestaTipo()))
                .toList();

        return createPreuniversitarioUseCase.createPreuniversitario(preuniversitarioAlumnos);
    }
}
