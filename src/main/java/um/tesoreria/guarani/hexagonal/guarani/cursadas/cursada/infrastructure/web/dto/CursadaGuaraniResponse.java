package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.PeriodoCursada;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursadaGuaraniResponse {
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
