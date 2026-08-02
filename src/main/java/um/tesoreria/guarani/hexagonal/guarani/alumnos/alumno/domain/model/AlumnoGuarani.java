package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.util.Jsonifier;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoGuarani {

    private Integer alumno;
    private String legajo;
    private Integer persona;
    private Integer propuesta;
    private Integer planVersion;
    private Integer ubicacion;
    private String modalidad;
    private Integer division;
    private Short anioCursada;
    private Short cantidadReadmisiones;
    private String regular;
    private String calidad;
    private BigDecimal coeficiente;

}
