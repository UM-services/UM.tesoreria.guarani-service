package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.CreatePersonalesPort;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.AlumnoGuaraniClient;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.model.NumeroDocumento;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatePersonalesAdapter implements CreatePersonalesPort {

    private final AlumnoGuaraniClient alumnoGuaraniClient;

    @Override
    public CreatePersonalesResponse createPersonales(AlumnoGuarani alumno) {
        PersonaGuarani persona = alumno == null ? null : alumno.getPersonaRel();
        var documento = persona == null ? null : persona.getDocumentoPrincipalRel();
        String nroDocumento = documento == null ? null : documento.getNroDocumento();

        var parseado = NumeroDocumento.parse(nroDocumento);
        if (parseado.isEmpty()) {
            log.warn("Sin parte numérica en nroDocumento '{}' (persona {}, alumno {}): no se envía a core",
                    nroDocumento, persona == null ? null : persona.getPersona(), alumno == null ? null : alumno.getAlumno());
            return falla(alumno);
        }
        var numero = parseado.get();

        if (persona.getPersona() == null) {
            persona.setPersona(alumno.getPersona());
        }
        documento.setNroDocumento(numero.getDigitos());
        persona.setNumeroPrefijo(numero.getPrefijo());
        persona.setNumeroPosfijo(numero.getPosfijo());
        log.debug("Personales hacia core -> documento: {} prefijo: '{}' posfijo: '{}' guaraniPersona: {}",
                numero.getDigitos(), numero.getPrefijo(), numero.getPosfijo(), persona.getPersona());

        var response = alumnoGuaraniClient.createPersonales(alumno);
        if (response == null) {
            log.error("core no devolvió respuesta para el documento {}", numero.getDigitos());
            return falla(alumno);
        }
        return response;
    }

    private CreatePersonalesResponse falla(AlumnoGuarani alumno) {
        return CreatePersonalesResponse.builder()
                .result(false)
                .alumnoGuarani(alumno)
                .build();
    }
}
