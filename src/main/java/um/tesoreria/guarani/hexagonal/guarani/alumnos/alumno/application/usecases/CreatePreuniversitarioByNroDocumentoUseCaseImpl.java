package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePersonalesByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePreuniversitarioByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePreuniversitarioByNroDocumentoUseCaseImpl implements CreatePreuniversitarioByNroDocumentoUseCase {

    private final CreatePersonalesByNroDocumentoUseCase createPersonalesByNroDocumentoUseCase;

    @Override
    public List<AlumnoGuarani> createPreuniversitarioByNroDocumento(String nroDocumento) {
        log.debug("\n\nCreating preuniversitario for document: {}\n\n", nroDocumento);
        List<CreatePersonalesResponse> creados = null;
        if ((creados = createPersonalesByNroDocumentoUseCase.createPersonalesByNroDocumento(nroDocumento)).isEmpty()) {
            return List.of();
        }

//        List<AlumnoGuarani> generados = new ArrayList<>();
//        for (var persona:creados) {
//            try {
//                log.debug("\n\nProcessing Alumno -> {}\n\n", Jsonifier.builder(alumno.getPersonaRel()).build());
//                AlumnoGuarani creado = alumnoGuaraniClient.createPreuniversitario(alumno);
//                if (creado != null) {
//                    creados.add(creado);
//                }
//            } catch (Exception e) {
//                log.error("Error creating preuniversitario for alumno {}: {}", alumno.getAlumno(), e.getMessage());
//            }
//        }
//        return creados;
        return null;
    }
}
