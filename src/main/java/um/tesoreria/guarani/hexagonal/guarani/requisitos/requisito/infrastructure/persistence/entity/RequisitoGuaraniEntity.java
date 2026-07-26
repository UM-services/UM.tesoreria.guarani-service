package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.entity.RequisitoTipoGuaraniEntity;

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

    @OneToOne(optional = true)
    @JoinColumn(name = "requisitoTipo", referencedColumnName = "requisitoTipo", insertable = false, updatable = false)
    private RequisitoTipoGuaraniEntity requisitoTipoRel;

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
