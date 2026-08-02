package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionTipoGuaraniResponse {

    private Integer ubicacionTipo;
    private String nombre;
    private String descripcion;
}
