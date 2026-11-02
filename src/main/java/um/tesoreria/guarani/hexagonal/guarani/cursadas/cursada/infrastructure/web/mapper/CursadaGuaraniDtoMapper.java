package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.web.dto.CursadaGuaraniResponse;

@Component
public class CursadaGuaraniDtoMapper {

    public CursadaGuaraniResponse toResponse(CursadaGuarani domain) {
        if (domain == null) {
            return null;
        }
        return CursadaGuaraniResponse.builder()
                .responsableAcademica(domain.getResponsableAcademica())
                .propuesta(domain.getPropuesta())
                .propuestaNombre(domain.getPropuestaNombre())
                .planVersion(domain.getPlanVersion())
                .actividadCodigo(domain.getActividadCodigo())
                .actividadNombre(domain.getActividadNombre())
                .anioPlan(domain.getAnioPlan())
                .periodo(domain.getPeriodo())
                .periodoLectivo(domain.getPeriodoLectivo())
                .periodoNombre(domain.getPeriodoNombre())
                .anioCursada(domain.getAnioCursada())
                .build();
    }
}
