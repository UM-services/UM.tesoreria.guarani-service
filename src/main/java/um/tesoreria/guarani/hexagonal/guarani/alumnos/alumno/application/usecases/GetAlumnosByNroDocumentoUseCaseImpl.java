package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.AlumnoGuaraniRepository;
import um.tesoreria.guarani.util.Jsonifier;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAlumnosByNroDocumentoUseCaseImpl implements GetAlumnosByNroDocumentoUseCase {

    private final AlumnoGuaraniRepository repository;

    @Override
    public List<AlumnoGuarani> getByNroDocumento(String nroDocumento) {
        var alumnos = repository.findAllByNroDocumento(nroDocumento);
        log.debug("Alumnos -> {}", Jsonifier.builder(alumnos).build());
        return alumnos;
    }
}
