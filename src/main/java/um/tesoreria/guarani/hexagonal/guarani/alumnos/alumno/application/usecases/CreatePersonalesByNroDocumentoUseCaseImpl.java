package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.CreatePersonalesByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.CreatePersonalesPort;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.PersonaCoreResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.application.service.PropuestaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.mapper.PropuestaGuaraniDtoMapper;
import um.tesoreria.guarani.util.Jsonifier;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePersonalesByNroDocumentoUseCaseImpl implements CreatePersonalesByNroDocumentoUseCase {

    private static final String SIN_VALOR = "";

    private final GetAlumnosByNroDocumentoUseCase getAlumnosByNroDocumentoUseCase;
    private final CreatePersonalesPort createPersonalesPort;
    private final PropuestaGuaraniService propuestaGuaraniService;
    private final PropuestaGuaraniDtoMapper propuestaGuaraniDtoMapper;

    @Override
    public List<CreatePersonalesResponse> createPersonalesByNroDocumento(String nroDocumento) {
        log.debug("\n\nCreating personales for document: {}\n\n", nroDocumento);

        log.debug("\n\nLeyendo desde Guarani\n\n");
        List<AlumnoGuarani> alumnos = getAlumnosByNroDocumentoUseCase.getByNroDocumento(nroDocumento);
        if (alumnos == null || alumnos.isEmpty()) {
            return List.of();
        }

        log.debug("\n\nCreando desde Guarani\n\n");
        List<CreatePersonalesResponse> created = new ArrayList<>();
        for (AlumnoGuarani alumno : alumnos) {
            var alumnoFull = createPersonalesPort.createPersonales(alumno);
            if (alumnoFull == null) {
                log.error("El puerto de personales no devolvió respuesta para el alumno {}", alumno.getAlumno());
                continue;
            }
            if (!alumnoFull.esCreado()) {
                log.warn("No se crearon los personales del alumno {}: {}", alumno.getAlumno(), Jsonifier.builder(alumnoFull).build());
                created.add(alumnoFull);
                continue;
            }
            if (alumno.getPropuesta() != null) {
                var propuestaGuarani = propuestaGuaraniService.getByPropuestaId(alumno.getPropuesta());
                alumnoFull.setPropuestaGuarani(propuestaGuaraniDtoMapper.toResponse(propuestaGuarani));
            }
            verificarNumeroDocumentoGuardado(alumno.getPersonaRel(), alumnoFull.getPersona());
            created.add(alumnoFull);
        }
        log.debug("\n\nAlumnos Creados -> {}\n\n", Jsonifier.builder(created).build());
        return created;
    }

    private void verificarNumeroDocumentoGuardado(PersonaGuarani personaGuarani, PersonaCoreResponse persona) {
        if (personaGuarani == null || persona == null) {
            return;
        }
        String prefijo = valor(personaGuarani.getNumeroPrefijo());
        String posfijo = valor(personaGuarani.getNumeroPosfijo());
        Long guaraniPersona = personaGuarani.getPersona() == null ? null : personaGuarani.getPersona().longValue();

        boolean guardado = prefijo.equals(valor(persona.getNumeroPrefijo()))
                && posfijo.equals(valor(persona.getNumeroPosfijo()))
                && (guaraniPersona == null || guaraniPersona.equals(persona.getGuaraniPersona()));
        if (!guardado) {
            log.warn("core devolvió la persona {} con prefijo '{}' posfijo '{}' guaraniPersona {}, pero se envió prefijo '{}' posfijo '{}' guaraniPersona {}. "
                            + "Verificar que tesoreria-core-service tenga el mapeo de numeroPrefijo/numeroPosfijo/guaraniPersona en create/personales",
                    persona.getUniqueId(), persona.getNumeroPrefijo(), persona.getNumeroPosfijo(), persona.getGuaraniPersona(),
                    prefijo, posfijo, guaraniPersona);
        }
    }

    private static String valor(String value) {
        return value == null ? SIN_VALOR : value;
    }

}
