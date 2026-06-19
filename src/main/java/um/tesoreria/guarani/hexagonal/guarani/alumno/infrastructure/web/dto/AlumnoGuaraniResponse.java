package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoGuaraniResponse {
    private Integer alumnoId;
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
