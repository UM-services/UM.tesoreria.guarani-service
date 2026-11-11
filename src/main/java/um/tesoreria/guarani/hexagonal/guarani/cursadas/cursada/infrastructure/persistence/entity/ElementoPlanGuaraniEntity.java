package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_elementos_plan", schema = "negocio")
public class ElementoPlanGuaraniEntity {

    @Id
    private Integer elementoPlan;

    private Integer planVersion;
    private Integer elementoRevision;
    private Short anioDeCursada;
    // Id de sga_periodos_genericos: el período en que el plan dicta la actividad (semestre, anual, ...).
    private Integer periodoDeCursada;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "elementoRevision", insertable = false, updatable = false)
    private ElementoRevisionGuaraniEntity elementoRevisionRel;

}
