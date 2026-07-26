package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.AlumnoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAlumnosByNroDocumentoUseCaseImpl implements GetAlumnosByNroDocumentoUseCase {

    private final AlumnoGuaraniRepository repository;

    @Override
    public List<AlumnoGuarani> getByNroDocumento(String nroDocumento) {
        return repository.findAllByNroDocumento(nroDocumento);
    }
}
