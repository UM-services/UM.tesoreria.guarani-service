package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model;

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
public class UbicacionTipoGuarani {

    private Integer ubicacionTipo;
    private String nombre;
    private String descripcion;
}
