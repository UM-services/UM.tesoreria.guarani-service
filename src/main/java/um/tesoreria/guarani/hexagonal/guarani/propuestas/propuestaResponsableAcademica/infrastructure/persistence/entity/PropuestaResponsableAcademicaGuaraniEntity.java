package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.entity.ResponsableAcademicaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PropuestaResponsableAcademicaGuaraniEntity.CompositeId.class)
@Table(name = "sga_propuestas_ra", schema = "negocio")
public class PropuestaResponsableAcademicaGuaraniEntity {

    @Id
    @Column(name = "propuesta", nullable = false)
    private Integer propuesta;

    @Id
    @Column(name = "responsable_academica", nullable = false)
    private Integer responsableAcademica;

    @Column(name = "informa_araucano_codigo_ua", columnDefinition = "bpchar", nullable = false)
    private String informaAraucanoCodigoUa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "propuesta", referencedColumnName = "propuesta", insertable = false, updatable = false)
    private PropuestaGuaraniEntity propuestaRel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "responsable_academica", referencedColumnName = "responsable_academica", insertable = false, updatable = false)
    private ResponsableAcademicaGuaraniEntity responsableAcademicaRel;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class CompositeId implements Serializable {
        private Integer propuesta;
        private Integer responsableAcademica;
    }
}
