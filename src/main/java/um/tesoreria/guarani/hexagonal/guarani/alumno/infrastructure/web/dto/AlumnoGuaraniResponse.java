package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.web.dto.PersonaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.web.dto.UbicacionGuaraniResponse;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoGuaraniResponse {
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

    private PersonaGuaraniResponse personaRel;
    private PropuestaGuaraniResponse propuestaRel;
    private UbicacionGuaraniResponse ubicacionRel;
}
