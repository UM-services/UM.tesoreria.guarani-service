package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.dto.PropuestaResponsableAcademicaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaTipo.infrastructure.web.dto.PropuestaTipoGuaraniResponse;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaGuaraniResponse {
    private Integer propuesta;
    private String nombre;
    private String nombreAbreviado;
    private String codigo;
    private Integer propuestaTipo;
    private PropuestaTipoGuaraniResponse propuestaTipoRel;
    private List<PropuestaResponsableAcademicaGuaraniResponse> responsablesAcademicas;
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
