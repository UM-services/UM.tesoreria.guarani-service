package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;

@Component
public class AlumnoGuaraniMapper {

    public AlumnoGuaraniEntity toEntity(AlumnoGuarani domain) {
        if (domain == null) return null;
        return AlumnoGuaraniEntity.builder()
                .alumno(domain.getAlumnoId())
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
                .build();
    }

    public AlumnoGuarani toDomain(AlumnoGuaraniEntity entity) {
        if (entity == null) return null;
        return AlumnoGuarani.builder()
                .alumnoId(entity.getAlumno())
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
                .build();
    }
}
