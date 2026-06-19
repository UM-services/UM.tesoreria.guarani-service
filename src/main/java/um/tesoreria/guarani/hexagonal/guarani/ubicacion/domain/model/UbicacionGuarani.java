package um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionGuarani {
    private Integer ubicacionId;
    private String nombre;
    private Integer ubicacionTipo;
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
