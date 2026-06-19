package um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;

@Component
public class PropuestaGuaraniDtoMapper {

    public PropuestaGuaraniResponse toResponse(PropuestaGuarani domain) {
        if (domain == null) return null;
        return PropuestaGuaraniResponse.builder()
                .propuestaId(domain.getPropuestaId())
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
}
