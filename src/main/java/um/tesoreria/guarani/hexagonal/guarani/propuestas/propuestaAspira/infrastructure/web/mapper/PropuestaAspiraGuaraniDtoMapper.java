package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.web.mapper.PersonaGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.web.dto.PropuestaAspiraGuaraniResponse;

@Component
public class PropuestaAspiraGuaraniDtoMapper {

    private final PersonaGuaraniDtoMapper personaMapper;

    public PropuestaAspiraGuaraniDtoMapper(PersonaGuaraniDtoMapper personaMapper) {
        this.personaMapper = personaMapper;
    }

    public PropuestaAspiraGuaraniResponse toResponse(PropuestaAspiraGuarani domain) {
        if (domain == null) return null;
        return PropuestaAspiraGuaraniResponse.builder()
                .propuestaAspira(domain.getPropuestaAspira())
                .persona(domain.getPersona())
                .personaRel(personaMapper.toResponse(domain.getPersonaRel()))
                .periodoInsc(domain.getPeriodoInsc())
                .anioAcademico(domain.getAnioAcademico())
                .propuesta(domain.getPropuesta())
                .planVersion(domain.getPlanVersion())
                .modalidad(domain.getModalidad())
                .ubicacion(domain.getUbicacion())
                .fechaInscripcion(domain.getFechaInscripcion())
                .fechaAlta(domain.getFechaAlta())
                .fechaRechazo(domain.getFechaRechazo())
                .tipoIngreso(domain.getTipoIngreso())
                .convenio(domain.getConvenio())
                .inscFueraDeTermino(domain.getInscFueraDeTermino())
                .motivoInscripcion(domain.getMotivoInscripcion())
                .situacionAsp(domain.getSituacionAsp())
                .observaciones(domain.getObservaciones())
                .situacionAspAnterior(domain.getSituacionAspAnterior())
                .observacionesRechazo(domain.getObservacionesRechazo())
                .nroTransaccion(domain.getNroTransaccion())
                .documentoIngreso(domain.getDocumentoIngreso())
                .sqToken(domain.getSqToken())
                .sqSincronizado(domain.getSqSincronizado())
                .datoEstudioAnterior(domain.getDatoEstudioAnterior())
                .interfaz(domain.getInterfaz())
                .build();
    }
}
