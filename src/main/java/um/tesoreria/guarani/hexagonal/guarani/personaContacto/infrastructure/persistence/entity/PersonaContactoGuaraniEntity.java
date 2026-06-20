package um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mdp_personas_contactos", schema = "negocio")
public class PersonaContactoGuaraniEntity {

    @Id
    private Integer personaContacto;

    private Integer persona;
    private String contactoTipo;
    private String otrosContactos;
    private String email;
    private String telefonoCodigoArea;
    private String telefonoNumero;

}
