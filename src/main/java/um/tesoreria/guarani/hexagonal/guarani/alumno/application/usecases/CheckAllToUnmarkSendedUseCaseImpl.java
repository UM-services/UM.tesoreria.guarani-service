package um.tesoreria.guarani.hexagonal.guarani.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.CheckAllToUnmarkSendedUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.AlumnoGuaraniClient;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.dto.AlumnoDeteccionRequest;
import um.tesoreria.guarani.util.Jsonifier;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckAllToUnmarkSendedUseCaseImpl implements CheckAllToUnmarkSendedUseCase {

    private final AlumnoGuaraniClient alumnoGuaraniClient;

    @Override
    public List<AlumnoDeteccionRequest> checkAllAlumnosWithoutChequera(List<AlumnoDeteccionRequest> alumnos) {
        log.debug("\n\nProcessing CheckAllToUnmarkSendedUseCaseImpl.checkAllAlumnosWithoutChequera\n\n");
        var alumnosPendientes = alumnoGuaraniClient.desmarcarEnviados(alumnos);
        log.debug("pendientes = {}", Jsonifier.builder(alumnosPendientes).build());
        return alumnosPendientes;
    }

}
