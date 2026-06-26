package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaTipoGuaraniResponse {
    private Integer propuestaTipo;
    private String descripcion;
    private String otorgaTitulo;
    private String reportaAraucano;
    private String permiteMatricular;
    private String permiteInscribir;
    private String controlRegularidad;
    private String disponibleEnAutogestion;
    private String informarMovilidadInternacional;
}
