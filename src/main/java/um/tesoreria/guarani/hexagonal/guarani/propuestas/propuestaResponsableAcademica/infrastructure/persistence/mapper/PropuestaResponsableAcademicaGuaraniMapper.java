package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.mapper.ResponsableAcademicaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.entity.PropuestaResponsableAcademicaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.mapper.PropuestaGuaraniMapper;

@Component
public class PropuestaResponsableAcademicaGuaraniMapper {
    private final PropuestaGuaraniMapper propuestaMapper;
    private final ResponsableAcademicaGuaraniMapper responsableAcademicaMapper;

    public PropuestaResponsableAcademicaGuaraniMapper(
            PropuestaGuaraniMapper propuestaMapper,
            ResponsableAcademicaGuaraniMapper responsableAcademicaMapper) {
        this.propuestaMapper = propuestaMapper;
        this.responsableAcademicaMapper = responsableAcademicaMapper;
    }

    public PropuestaResponsableAcademicaGuarani toDomain(PropuestaResponsableAcademicaGuaraniEntity entity) {
        if (entity == null) {
            return null;
        }
        return PropuestaResponsableAcademicaGuarani.builder()
                .propuesta(entity.getPropuesta())
                .propuestaRel(propuestaMapper.toDomain(entity.getPropuestaRel()))
                .responsableAcademica(entity.getResponsableAcademica())
                .responsableAcademicaRel(responsableAcademicaMapper.toDomain(entity.getResponsableAcademicaRel()))
                .informaAraucanoCodigoUa(entity.getInformaAraucanoCodigoUa())
                .build();
    }

    public PropuestaResponsableAcademicaGuarani toDomainWithoutPropuesta(
            PropuestaResponsableAcademicaGuaraniEntity entity) {
        if (entity == null) {
            return null;
        }
        return PropuestaResponsableAcademicaGuarani.builder()
                .propuesta(entity.getPropuesta())
                .responsableAcademica(entity.getResponsableAcademica())
                .responsableAcademicaRel(responsableAcademicaMapper.toDomain(entity.getResponsableAcademicaRel()))
                .informaAraucanoCodigoUa(entity.getInformaAraucanoCodigoUa())
                .build();
    }
}
