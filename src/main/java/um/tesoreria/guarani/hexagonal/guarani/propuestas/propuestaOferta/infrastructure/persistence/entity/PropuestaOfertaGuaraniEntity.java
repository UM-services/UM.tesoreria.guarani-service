package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PropuestaOfertaGuaraniEntity.CompositeId.class)
@Table(name = "sga_propuestas_oferta", schema = "negocio")
public class PropuestaOfertaGuaraniEntity {

    @Id
    @Column(name = "propuesta", nullable = false)
    private Integer propuesta;

    @Id
    @Column(name = "ubicacion", nullable = false)
    private Integer ubicacion;

    @Column(name = "institucion_araucano")
    private Integer institucionAraucano;

    @ManyToOne(optional = false)
    @JoinColumn(name = "propuesta", referencedColumnName = "propuesta", insertable = false, updatable = false)
    private PropuestaGuaraniEntity propuestaRel;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class CompositeId implements Serializable {
        private Integer propuesta;
        private Integer ubicacion;
    }
}
