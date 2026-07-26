package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoTipoGuaraniResponse {
    private Integer requisitoTipo;
    private String nombre;
    private String descripcion;
    private Integer regla;

    @Builder.Default
    private String publico = "N";

    private String icono;
}
