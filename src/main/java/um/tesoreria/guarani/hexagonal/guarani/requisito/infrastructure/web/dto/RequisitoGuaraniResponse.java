package um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoGuaraniResponse {
    private Integer requisito;
    private String nombre;
    private Integer requisitoTipo;
    private Integer requisitoSubordinadoDe;
    private String descripcion;
    private Integer regla;
    private Integer cacheable;
    private String requisitoIngreso;
    private String activo;
    private String tipoDocumentoDigital;
    private String requisitoEgreso;
    private String aplicaABecas;
}
