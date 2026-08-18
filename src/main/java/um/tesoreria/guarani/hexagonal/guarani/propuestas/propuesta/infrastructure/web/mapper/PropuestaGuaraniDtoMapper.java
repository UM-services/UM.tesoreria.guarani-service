package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.mapper.PropuestaResponsableAcademicaGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaTipo.infrastructure.web.mapper.PropuestaTipoGuaraniDtoMapper;

@Component
public class PropuestaGuaraniDtoMapper {

    private final PropuestaTipoGuaraniDtoMapper propuestaTipoDtoMapper;
    private final PropuestaResponsableAcademicaGuaraniDtoMapper responsablesAcademicasDtoMapper;

    public PropuestaGuaraniDtoMapper(
            PropuestaTipoGuaraniDtoMapper propuestaTipoDtoMapper,
            @Lazy PropuestaResponsableAcademicaGuaraniDtoMapper responsablesAcademicasDtoMapper) {
        this.propuestaTipoDtoMapper = propuestaTipoDtoMapper;
        this.responsablesAcademicasDtoMapper = responsablesAcademicasDtoMapper;
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
                .responsablesAcademicas(domain.getResponsablesAcademicas() == null
                        ? java.util.List.of()
                        : domain.getResponsablesAcademicas().stream()
                        .map(responsablesAcademicasDtoMapper::toResponseWithoutPropuesta)
                        .toList())
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
