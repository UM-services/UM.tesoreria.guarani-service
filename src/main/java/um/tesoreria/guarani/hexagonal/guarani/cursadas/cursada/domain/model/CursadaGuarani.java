package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursadaGuarani {

    // Id de la inscripción: desempata las claves repetidas y no sale en la respuesta.
    private Integer inscripcion;
    private Integer responsableAcademica;
    private Integer propuesta;
    private String propuestaNombre;
    private Integer planVersion;
    private String actividadCodigo;
    private String actividadNombre;
    private Integer anioPlan;
    private PeriodoCursada periodo;
    private Integer periodoLectivo;
    private String periodoNombre;
    private Short anioCursada;

}
