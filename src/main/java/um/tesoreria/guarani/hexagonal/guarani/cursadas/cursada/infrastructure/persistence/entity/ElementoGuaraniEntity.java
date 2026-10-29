package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_elementos", schema = "negocio")
public class ElementoGuaraniEntity {

    @Id
    private Integer elemento;

    private String codigo;
    private String nombre;

}
