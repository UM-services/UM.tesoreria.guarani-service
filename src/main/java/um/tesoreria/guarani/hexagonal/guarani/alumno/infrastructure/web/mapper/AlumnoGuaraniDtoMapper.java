package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.dto.AlumnoGuaraniResponse;

@Component
public class AlumnoGuaraniDtoMapper {

    public AlumnoGuaraniResponse toResponse(AlumnoGuarani domain) {
        if (domain == null) return null;
        return AlumnoGuaraniResponse.builder()
                .alumnoId(domain.getAlumnoId())
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
}
