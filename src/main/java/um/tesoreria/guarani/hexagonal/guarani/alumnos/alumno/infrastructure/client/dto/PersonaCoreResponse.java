package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaCoreResponse {

    private Integer uniqueId;
    private Integer personaId;
    private Integer documentoId;
    private String apellido;
    private String nombre;
    private String sexo;
    private String primero;
    private String cuit;
    private String cbu;
    private String password;
    private String hpum;

}
