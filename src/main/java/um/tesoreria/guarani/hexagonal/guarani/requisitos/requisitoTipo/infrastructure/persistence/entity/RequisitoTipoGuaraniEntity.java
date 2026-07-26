package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_requisitos_tipos", schema = "negocio")
public class RequisitoTipoGuaraniEntity {

    @Id
    private Integer requisitoTipo;

    private String nombre;
    private String descripcion;
    private Integer regla;

    @Builder.Default
    @Column(columnDefinition = "bpchar")
    private String publico = "N";

    private String icono;

}
