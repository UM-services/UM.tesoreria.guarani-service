package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.model;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model.UbicacionTipoGuarani;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionGuarani {
    private Integer ubicacion;
    private String nombre;
    private Integer ubicacionTipo;
    private UbicacionTipoGuarani ubicacionTipoRel;
    private Integer localidad;
    private String calle;
    private String numero;
    private String codigoPostal;
    private String telefono;
    private String fax;
    private String email;
    private Integer institucionAraucano;
    private BigDecimal latitud;
    private BigDecimal longitud;
}
