package um.tesoreria.guarani.hexagonal.guarani.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.CheckAllToUnmarkSendedUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.AlumnoGuaraniClient;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.dto.AlumnoDeteccionRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckAllToUnmarkSendedUseCaseImpl implements CheckAllToUnmarkSendedUseCase {

    private final AlumnoGuaraniClient alumnoGuaraniClient;

    @Override
    public List<AlumnoDeteccionRequest> checkAllAlumnosWithoutChequera(List<AlumnoDeteccionRequest> alumnos) {
        return alumnoGuaraniClient.desmarcarEnviados(alumnos);
    }

}
