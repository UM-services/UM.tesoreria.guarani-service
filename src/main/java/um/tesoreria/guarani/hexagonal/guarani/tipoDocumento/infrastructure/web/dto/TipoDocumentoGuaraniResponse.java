package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumentoGuaraniResponse {
    private Short tipoDocumento;
    private String descripcion;
    private String descAbreviada;
    private Integer ordenPrincipal;
    private String habilitaInscripcion;
    private String tipoDeDato;
    private String puedeEliminarse;
    private String expRegularValidacion;
    private String expRegularMensaje;
}
