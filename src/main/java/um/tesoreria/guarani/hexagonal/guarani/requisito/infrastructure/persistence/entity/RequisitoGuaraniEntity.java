package um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_requisitos", schema = "negocio")
public class RequisitoGuaraniEntity {

    @Id
    private Integer requisito;

    private String nombre;
    private Integer requisitoTipo;
    private Integer requisitoSubordinadoDe;
    private String descripcion;
    private Integer regla;
    private Integer cacheable;

    @Column(columnDefinition = "bpchar")
    private String requisitoIngreso;

    @Column(columnDefinition = "bpchar")
    private String activo;

    private String tipoDocumentoDigital;

    @Column(columnDefinition = "bpchar")
    private String requisitoEgreso;

    @Column(name = "aplica_a_becas", columnDefinition = "bpchar")
    private String aplicaABecas;

}
