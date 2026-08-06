package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.CreatePersonalesPort;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.AlumnoGuaraniClient;

@Component
@RequiredArgsConstructor
public class CreatePersonalesAdapter implements CreatePersonalesPort {

    private final AlumnoGuaraniClient alumnoGuaraniClient;

    @Override
    public Boolean createPersonales(AlumnoGuarani alumno) {
        return alumnoGuaraniClient.createPersonales(alumno);
    }
}
