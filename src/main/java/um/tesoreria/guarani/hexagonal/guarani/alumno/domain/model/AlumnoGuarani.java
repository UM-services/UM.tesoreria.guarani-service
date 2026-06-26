package um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;
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

    private PersonaGuarani personaRel;
    private PropuestaGuarani propuestaRel;
    private UbicacionGuarani ubicacionRel;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }
}
