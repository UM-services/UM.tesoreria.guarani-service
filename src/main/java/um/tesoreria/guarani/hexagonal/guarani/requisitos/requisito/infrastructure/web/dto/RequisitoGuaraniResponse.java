package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.web.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.web.dto.RequisitoTipoGuaraniResponse;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoGuaraniResponse {
    private Integer requisito;
    private String nombre;
    private Integer requisitoTipo;
    private RequisitoTipoGuaraniResponse requisitoTipoRel;
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
