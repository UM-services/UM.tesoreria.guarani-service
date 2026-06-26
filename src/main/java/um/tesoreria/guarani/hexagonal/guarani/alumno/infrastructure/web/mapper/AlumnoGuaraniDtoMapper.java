package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.dto.AlumnoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.web.mapper.PersonaGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.mapper.PropuestaGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.web.mapper.UbicacionGuaraniDtoMapper;

@Component
public class AlumnoGuaraniDtoMapper {

    private final PersonaGuaraniDtoMapper personaDtoMapper;
    private final PropuestaGuaraniDtoMapper propuestaDtoMapper;
    private final UbicacionGuaraniDtoMapper ubicacionDtoMapper;

    public AlumnoGuaraniDtoMapper(PersonaGuaraniDtoMapper personaDtoMapper, PropuestaGuaraniDtoMapper propuestaDtoMapper, UbicacionGuaraniDtoMapper ubicacionDtoMapper) {
        this.personaDtoMapper = personaDtoMapper;
        this.propuestaDtoMapper = propuestaDtoMapper;
        this.ubicacionDtoMapper = ubicacionDtoMapper;
    }

    public AlumnoGuaraniResponse toResponse(AlumnoGuarani domain) {
        if (domain == null) return null;
        return AlumnoGuaraniResponse.builder()
                .alumno(domain.getAlumno())
                .legajo(domain.getLegajo())
                .persona(domain.getPersona())
                .propuesta(domain.getPropuesta())
                .planVersion(domain.getPlanVersion())
                .ubicacion(domain.getUbicacion())
                .modalidad(domain.getModalidad())
                .division(domain.getDivision())
                .anioCursada(domain.getAnioCursada())
                .cantidadReadmisiones(domain.getCantidadReadmisiones())
                .regular(domain.getRegular())
                .calidad(domain.getCalidad())
                .coeficiente(domain.getCoeficiente())
                .personaRel(personaDtoMapper.toResponse(domain.getPersonaRel()))
                .propuestaRel(propuestaDtoMapper.toResponse(domain.getPropuestaRel()))
                .ubicacionRel(ubicacionDtoMapper.toResponse(domain.getUbicacionRel()))
                .build();
    }
}
