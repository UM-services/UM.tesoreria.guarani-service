package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_elementos_revision", schema = "negocio")
public class ElementoRevisionGuaraniEntity {

    @Id
    private Integer elementoRevision;

    private Integer elemento;

}
