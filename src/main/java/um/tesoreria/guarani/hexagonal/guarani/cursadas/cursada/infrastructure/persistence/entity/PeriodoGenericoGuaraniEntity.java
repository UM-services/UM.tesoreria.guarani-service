package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_periodos_genericos", schema = "negocio")
public class PeriodoGenericoGuaraniEntity {

    @Id
    private Integer periodoGenerico;

    private String nombre;

}
