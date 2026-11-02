package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_periodos_lectivos", schema = "negocio")
public class PeriodoLectivoGuaraniEntity {

    @Id
    private Integer periodoLectivo;

    private Integer periodo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "periodo", insertable = false, updatable = false)
    private PeriodoGuaraniEntity periodoRel;

}
