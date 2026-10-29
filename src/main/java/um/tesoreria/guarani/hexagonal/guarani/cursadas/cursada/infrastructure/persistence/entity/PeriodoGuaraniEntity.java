package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_periodos", schema = "negocio")
public class PeriodoGuaraniEntity {

    @Id
    private Integer periodo;

    private String nombre;
    private BigDecimal anioAcademico;
    private Integer periodoGenerico;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "periodoGenerico", insertable = false, updatable = false)
    private PeriodoGenericoGuaraniEntity periodoGenericoRel;

}
