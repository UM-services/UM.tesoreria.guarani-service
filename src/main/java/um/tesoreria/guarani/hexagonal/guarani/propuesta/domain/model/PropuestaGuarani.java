package um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaGuarani {
    private Integer propuestaId;
    private String nombre;
    private String nombreAbreviado;
    private String codigo;
    private Integer propuestaTipo;
    private PropuestaTipoGuarani propuestaTipoRel;
    private String publica;
    private Integer documentoAlta;
    private LocalDate fechaAlta;
    private Integer campoDisciplinar;
    private Integer escalaCumplimiento;
    private Integer documentoBaja;
    private LocalDate fechaBaja;
    private Short aTermino;
    private Integer entidad;
    private String estado;
}
