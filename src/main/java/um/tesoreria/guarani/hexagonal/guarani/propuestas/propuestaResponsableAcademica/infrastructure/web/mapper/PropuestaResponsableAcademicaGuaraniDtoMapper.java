package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.dto.PropuestaResponsableAcademicaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.web.mapper.ResponsableAcademicaGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.mapper.PropuestaGuaraniDtoMapper;

@Component
public class PropuestaResponsableAcademicaGuaraniDtoMapper {
    private final PropuestaGuaraniDtoMapper propuestaMapper;
    private final ResponsableAcademicaGuaraniDtoMapper responsableAcademicaMapper;

    public PropuestaResponsableAcademicaGuaraniDtoMapper(
            PropuestaGuaraniDtoMapper propuestaMapper,
            ResponsableAcademicaGuaraniDtoMapper responsableAcademicaMapper) {
        this.propuestaMapper = propuestaMapper;
        this.responsableAcademicaMapper = responsableAcademicaMapper;
    }

    public PropuestaResponsableAcademicaGuaraniResponse toResponse(PropuestaResponsableAcademicaGuarani domain) {
        if (domain == null) {
            return null;
        }
        return PropuestaResponsableAcademicaGuaraniResponse.builder()
                .propuesta(domain.getPropuesta())
                .propuestaRel(propuestaMapper.toResponse(domain.getPropuestaRel()))
                .responsableAcademica(domain.getResponsableAcademica())
                .responsableAcademicaRel(responsableAcademicaMapper.toResponse(domain.getResponsableAcademicaRel()))
                .informaAraucanoCodigoUa(domain.getInformaAraucanoCodigoUa())
                .build();
    }

    public PropuestaResponsableAcademicaGuaraniResponse toResponseWithoutPropuesta(
            PropuestaResponsableAcademicaGuarani domain) {
        if (domain == null) {
            return null;
        }
        return PropuestaResponsableAcademicaGuaraniResponse.builder()
                .propuesta(domain.getPropuesta())
                .responsableAcademica(domain.getResponsableAcademica())
                .responsableAcademicaRel(responsableAcademicaMapper.toResponse(domain.getResponsableAcademicaRel()))
                .informaAraucanoCodigoUa(domain.getInformaAraucanoCodigoUa())
                .build();
    }
}
