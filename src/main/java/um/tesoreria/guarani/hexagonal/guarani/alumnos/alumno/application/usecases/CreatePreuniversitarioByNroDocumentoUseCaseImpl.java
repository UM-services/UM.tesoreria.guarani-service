package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePersonalesByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePreuniversitarioByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.AlumnoGuaraniClient;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;
import um.tesoreria.guarani.util.Jsonifier;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePreuniversitarioByNroDocumentoUseCaseImpl implements CreatePreuniversitarioByNroDocumentoUseCase {

    private final CreatePersonalesByNroDocumentoUseCase createPersonalesByNroDocumentoUseCase;
    private final AlumnoGuaraniClient alumnoGuaraniClient;

    @Override
    public List<AlumnoGuarani> createPreuniversitarioByNroDocumento(String nroDocumento) {
        log.debug("\n\nCreating preuniversitario for document: {}\n\n", nroDocumento);
        List<CreatePersonalesResponse> creados;
        if ((creados = createPersonalesByNroDocumentoUseCase.createPersonalesByNroDocumento(nroDocumento)).isEmpty()) {
            return List.of();
        }

        var preuniversitariosCreados = new ArrayList<AlumnoGuarani>();
        for (var alumno:creados) {
            try {
                log.debug("\n\nProcessing Alumno -> {}\n\n", Jsonifier.builder(alumno.getAlumnoGuarani().getPersonaRel()).build());
                AlumnoGuarani preuniversitarioCreado = alumnoGuaraniClient.createPreuniversitario(alumno);
                if (preuniversitarioCreado != null) {
                    preuniversitariosCreados.add(preuniversitarioCreado);
                }
            } catch (Exception e) {
                log.error("Error creating preuniversitario for alumno {}: {}", alumno.getAlumnoGuarani().getAlumno(), e.getMessage());
            }
        }
        return preuniversitariosCreados;
   }
}
