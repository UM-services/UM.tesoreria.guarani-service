package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.entity.UbicacionTipoGuaraniEntity;

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

    @Column(name = "ubicacion_tipo")
    private Integer ubicacionTipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_tipo", referencedColumnName = "ubicacion_tipo", insertable = false, updatable = false)
    private UbicacionTipoGuaraniEntity ubicacionTipoRel;

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
