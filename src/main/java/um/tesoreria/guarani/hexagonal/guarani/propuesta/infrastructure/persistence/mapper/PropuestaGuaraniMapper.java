package um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;

@Component
public class PropuestaGuaraniMapper {

    public PropuestaGuaraniEntity toEntity(PropuestaGuarani domain) {
        if (domain == null) return null;
        return PropuestaGuaraniEntity.builder()
                .propuesta(domain.getPropuestaId())
                .nombre(domain.getNombre())
                .nombreAbreviado(domain.getNombreAbreviado())
                .codigo(domain.getCodigo())
                .propuestaTipo(domain.getPropuestaTipo())
                .publica(domain.getPublica())
                .documentoAlta(domain.getDocumentoAlta())
                .fechaAlta(domain.getFechaAlta())
                .campoDisciplinar(domain.getCampoDisciplinar())
                .escalaCumplimiento(domain.getEscalaCumplimiento())
                .documentoBaja(domain.getDocumentoBaja())
                .fechaBaja(domain.getFechaBaja())
                .aTermino(domain.getATermino())
                .entidad(domain.getEntidad())
                .estado(domain.getEstado())
                .build();
    }

    public PropuestaGuarani toDomain(PropuestaGuaraniEntity entity) {
        if (entity == null) return null;
        return PropuestaGuarani.builder()
                .propuestaId(entity.getPropuesta())
                .nombre(entity.getNombre())
                .nombreAbreviado(entity.getNombreAbreviado())
                .codigo(entity.getCodigo())
                .propuestaTipo(entity.getPropuestaTipo())
                .publica(entity.getPublica())
                .documentoAlta(entity.getDocumentoAlta())
                .fechaAlta(entity.getFechaAlta())
                .campoDisciplinar(entity.getCampoDisciplinar())
                .escalaCumplimiento(entity.getEscalaCumplimiento())
                .documentoBaja(entity.getDocumentoBaja())
                .fechaBaja(entity.getFechaBaja())
                .aTermino(entity.getATermino())
                .entidad(entity.getEntidad())
                .estado(entity.getEstado())
                .build();
    }
}
