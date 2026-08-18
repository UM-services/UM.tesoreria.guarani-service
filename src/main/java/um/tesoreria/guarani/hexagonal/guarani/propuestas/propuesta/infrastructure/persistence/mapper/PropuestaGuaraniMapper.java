package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.mapper.PropuestaResponsableAcademicaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaTipo.infrastructure.persistence.mapper.PropuestaTipoGuaraniMapper;

import java.util.List;
import java.util.Objects;

@Component
public class PropuestaGuaraniMapper {

    private final PropuestaTipoGuaraniMapper propuestaTipoMapper;
    private final PropuestaResponsableAcademicaGuaraniMapper responsablesAcademicasMapper;

    public PropuestaGuaraniMapper(
            PropuestaTipoGuaraniMapper propuestaTipoMapper,
            @Lazy PropuestaResponsableAcademicaGuaraniMapper responsablesAcademicasMapper) {
        this.propuestaTipoMapper = propuestaTipoMapper;
        this.responsablesAcademicasMapper = responsablesAcademicasMapper;
    }

    public PropuestaGuaraniEntity toEntity(PropuestaGuarani domain) {
        if (domain == null) return null;
        return PropuestaGuaraniEntity.builder()
                .propuesta(domain.getPropuesta())
                .nombre(domain.getNombre())
                .nombreAbreviado(domain.getNombreAbreviado())
                .codigo(domain.getCodigo())
                .propuestaTipo(domain.getPropuestaTipo())
                .propuestaTipoRel(propuestaTipoMapper.toEntity(domain.getPropuestaTipoRel()))
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
                .propuesta(entity.getPropuesta())
                .nombre(entity.getNombre())
                .nombreAbreviado(entity.getNombreAbreviado())
                .codigo(entity.getCodigo())
                .propuestaTipo(entity.getPropuestaTipo())
                .propuestaTipoRel(propuestaTipoMapper.toDomain(entity.getPropuestaTipoRel()))
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
                .responsablesAcademicas(entity.getResponsablesAcademicas() == null
                        ? List.of()
                        : entity.getResponsablesAcademicas().stream()
                        .map(responsablesAcademicasMapper::toDomainWithoutPropuesta)
                        .filter(Objects::nonNull)
                        .toList())
                .build();
    }
}
