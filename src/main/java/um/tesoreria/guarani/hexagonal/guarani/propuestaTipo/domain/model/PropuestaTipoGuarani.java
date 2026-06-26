package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaTipoGuarani {
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
