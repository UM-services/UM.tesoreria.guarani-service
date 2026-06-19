package um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_ubicaciones", schema = "negocio")
public class UbicacionGuaraniEntity {

    @Id
    private Integer ubicacion;

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
