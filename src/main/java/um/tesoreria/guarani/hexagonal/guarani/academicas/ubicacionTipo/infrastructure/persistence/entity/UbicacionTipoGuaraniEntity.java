package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_ubicaciones_tipos", schema = "negocio")
public class UbicacionTipoGuaraniEntity {

    @Id
    @Column(name = "ubicacion_tipo")
    private Integer ubicacionTipo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 255)
    private String descripcion;
}
