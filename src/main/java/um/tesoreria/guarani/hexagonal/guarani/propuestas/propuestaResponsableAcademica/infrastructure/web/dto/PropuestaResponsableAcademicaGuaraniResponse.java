package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.web.dto.ResponsableAcademicaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaResponsableAcademicaGuaraniResponse {
    private Integer propuesta;
    private Integer responsableAcademica;
    private PropuestaGuaraniResponse propuestaRel;
    private ResponsableAcademicaGuaraniResponse responsableAcademicaRel;
    private String informaAraucanoCodigoUa;
}
