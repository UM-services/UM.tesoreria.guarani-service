package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.infrastructure.web.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.web.dto.UbicacionTipoGuaraniResponse;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionGuaraniResponse {
    private Integer ubicacion;
    private String nombre;
    private Integer ubicacionTipo;
    private UbicacionTipoGuaraniResponse ubicacionTipoRel;
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
