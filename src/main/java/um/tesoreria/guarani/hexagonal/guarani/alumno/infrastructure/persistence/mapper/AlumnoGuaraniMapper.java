package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.persistence.mapper.PersonaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.mapper.PropuestaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.mapper.UbicacionGuaraniMapper;

@Component
public class AlumnoGuaraniMapper {

    private final PersonaGuaraniMapper personaMapper;
    private final PropuestaGuaraniMapper propuestaMapper;
    private final UbicacionGuaraniMapper ubicacionMapper;

    public AlumnoGuaraniMapper(PersonaGuaraniMapper personaMapper, PropuestaGuaraniMapper propuestaMapper, UbicacionGuaraniMapper ubicacionMapper) {
        this.personaMapper = personaMapper;
        this.propuestaMapper = propuestaMapper;
        this.ubicacionMapper = ubicacionMapper;
    }

    public AlumnoGuaraniEntity toEntity(AlumnoGuarani domain) {
        if (domain == null) return null;
        return AlumnoGuaraniEntity.builder()
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
                .personaRel(personaMapper.toEntity(domain.getPersonaRel()))
                .propuestaRel(propuestaMapper.toEntity(domain.getPropuestaRel()))
                .ubicacionRel(ubicacionMapper.toEntity(domain.getUbicacionRel()))
                .build();
    }

    public AlumnoGuarani toDomain(AlumnoGuaraniEntity entity) {
        if (entity == null) return null;
        return AlumnoGuarani.builder()
                .alumno(entity.getAlumno())
                .legajo(entity.getLegajo())
                .persona(entity.getPersona())
                .propuesta(entity.getPropuesta())
                .planVersion(entity.getPlanVersion())
                .ubicacion(entity.getUbicacion())
                .modalidad(entity.getModalidad())
                .division(entity.getDivision())
                .anioCursada(entity.getAnioCursada())
                .cantidadReadmisiones(entity.getCantidadReadmisiones())
                .regular(entity.getRegular())
                .calidad(entity.getCalidad())
                .coeficiente(entity.getCoeficiente())
                .personaRel(personaMapper.toDomain(entity.getPersonaRel()))
                .propuestaRel(propuestaMapper.toDomain(entity.getPropuestaRel()))
                .ubicacionRel(ubicacionMapper.toDomain(entity.getUbicacionRel()))
                .build();
    }
}
