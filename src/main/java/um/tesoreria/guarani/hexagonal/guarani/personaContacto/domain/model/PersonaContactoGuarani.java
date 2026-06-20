package um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaContactoGuarani {
    private Integer personaContacto;
    private Integer persona;
    private String contactoTipo;
    private String otrosContactos;
    private String email;
    private String telefonoCodigoArea;
    private String telefonoNumero;
}
