package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.model.ResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaResponsableAcademicaGuarani {
    private Integer propuesta;
    private PropuestaGuarani propuestaRel;
    private Integer responsableAcademica;
    private ResponsableAcademicaGuarani responsableAcademicaRel;
    private String informaAraucanoCodigoUa;
}
