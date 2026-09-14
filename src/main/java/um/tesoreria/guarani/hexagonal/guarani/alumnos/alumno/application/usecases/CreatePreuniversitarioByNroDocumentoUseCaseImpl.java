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
        for (var alumno : creados) {
            if (!alumnoEstaCreado(alumno)) {
                continue;
            }
            try {
                log.debug("\n\nProcessing Alumno -> {}\n\n", Jsonifier.builder(alumno.getAlumnoGuarani().getPersonaRel()).build());
                AlumnoGuarani preuniversitarioCreado = alumnoGuaraniClient.createPreuniversitario(alumno);
                if (preuniversitarioCreado != null) {
                    preuniversitariosCreados.add(preuniversitarioCreado);
                }
            } catch (Exception e) {
                log.error("Error creating preuniversitario for alumno {}: {}", alumnoId(alumno), e.getMessage());
            }
        }
        return preuniversitariosCreados;
    }

    private boolean alumnoEstaCreado(CreatePersonalesResponse alumno) {
        if (alumno == null || !alumno.esCreado()) {
            log.warn("Se omite el preuniversitario: core no creó los personales del documento consultado (alumno {})", alumnoId(alumno));
            return false;
        }
        if (alumno.getAlumnoGuarani() == null) {
            log.warn("Se omite el preuniversitario: core no devolvió el alumno para la persona {}",
                    alumno.getPersona().getUniqueId());
            return false;
        }
        if (alumno.getPropuestaGuarani() == null
                || alumno.getPropuestaGuarani().getResponsablesAcademicas() == null
                || alumno.getPropuestaGuarani().getResponsablesAcademicas().isEmpty()) {
            log.warn("Se omite el preuniversitario del alumno {}: la propuesta no tiene responsable académica",
                    alumnoId(alumno));
            return false;
        }
        return true;
    }

    private static Integer alumnoId(CreatePersonalesResponse alumno) {
        return alumno == null || alumno.getAlumnoGuarani() == null ? null : alumno.getAlumnoGuarani().getAlumno();
    }
}
