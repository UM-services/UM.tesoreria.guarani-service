package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePersonalesByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.CreatePersonalesPort;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePersonalesByNroDocumentoUseCaseImpl implements CreatePersonalesByNroDocumentoUseCase {

    private final GetAlumnosByNroDocumentoUseCase getAlumnosByNroDocumentoUseCase;
    private final CreatePersonalesPort createPersonalesPort;

    @Override
    public Boolean createPersonalesByNroDocumento(String nroDocumento) {
        log.debug("Creating personales for document: {}", nroDocumento);

        List<AlumnoGuarani> alumnos = getAlumnosByNroDocumentoUseCase.getByNroDocumento(nroDocumento);
        if (alumnos == null || alumnos.isEmpty()) {
            return false;
        }

        boolean created = true;
        for (AlumnoGuarani alumno : alumnos) {
            created = Boolean.TRUE.equals(createPersonalesPort.createPersonales(alumno)) && created;
        }
        return created;
    }
}
