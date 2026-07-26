package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.entity.PropuestaAspiraGuaraniEntity;

@Component
public class PropuestaAspiraGuaraniMapper {

    public PropuestaAspiraGuaraniEntity toEntity(PropuestaAspiraGuarani domain) {
        if (domain == null) return null;
        return PropuestaAspiraGuaraniEntity.builder()
                .propuestaAspira(domain.getPropuestaAspira())
                .persona(domain.getPersona())
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

    public PropuestaAspiraGuarani toDomain(PropuestaAspiraGuaraniEntity entity) {
        if (entity == null) return null;
        return PropuestaAspiraGuarani.builder()
                .propuestaAspira(entity.getPropuestaAspira())
                .persona(entity.getPersona())
                .periodoInsc(entity.getPeriodoInsc())
                .anioAcademico(entity.getAnioAcademico())
                .propuesta(entity.getPropuesta())
                .planVersion(entity.getPlanVersion())
                .modalidad(entity.getModalidad())
                .ubicacion(entity.getUbicacion())
                .fechaInscripcion(entity.getFechaInscripcion())
                .fechaAlta(entity.getFechaAlta())
                .fechaRechazo(entity.getFechaRechazo())
                .tipoIngreso(entity.getTipoIngreso())
                .convenio(entity.getConvenio())
                .inscFueraDeTermino(entity.getInscFueraDeTermino())
                .motivoInscripcion(entity.getMotivoInscripcion())
                .situacionAsp(entity.getSituacionAsp())
                .observaciones(entity.getObservaciones())
                .situacionAspAnterior(entity.getSituacionAspAnterior())
                .observacionesRechazo(entity.getObservacionesRechazo())
                .nroTransaccion(entity.getNroTransaccion())
                .documentoIngreso(entity.getDocumentoIngreso())
                .sqToken(entity.getSqToken())
                .sqSincronizado(entity.getSqSincronizado())
                .datoEstudioAnterior(entity.getDatoEstudioAnterior())
                .interfaz(entity.getInterfaz())
                .build();
    }
}
