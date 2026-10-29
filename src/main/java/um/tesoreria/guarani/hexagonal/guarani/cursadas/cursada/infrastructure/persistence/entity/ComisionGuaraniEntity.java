package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_comisiones", schema = "negocio")
public class ComisionGuaraniEntity {

    @Id
    private Integer comision;

    private Integer elemento;
    private Integer periodoLectivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "elemento", insertable = false, updatable = false)
    private ElementoGuaraniEntity elementoRel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "periodoLectivo", insertable = false, updatable = false)
    private PeriodoLectivoGuaraniEntity periodoLectivoRel;

}
