package um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.web.mapper.PropuestaTipoGuaraniDtoMapper;

@Component
public class PropuestaGuaraniDtoMapper {

    private final PropuestaTipoGuaraniDtoMapper propuestaTipoDtoMapper;

    public PropuestaGuaraniDtoMapper(PropuestaTipoGuaraniDtoMapper propuestaTipoDtoMapper) {
        this.propuestaTipoDtoMapper = propuestaTipoDtoMapper;
    }

    public PropuestaGuaraniResponse toResponse(PropuestaGuarani domain) {
        if (domain == null) return null;
        return PropuestaGuaraniResponse.builder()
                .propuesta(domain.getPropuesta())
                .nombre(domain.getNombre())
                .nombreAbreviado(domain.getNombreAbreviado())
                .codigo(domain.getCodigo())
                .propuestaTipo(domain.getPropuestaTipo())
                .propuestaTipoRel(propuestaTipoDtoMapper.toResponse(domain.getPropuestaTipoRel()))
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
