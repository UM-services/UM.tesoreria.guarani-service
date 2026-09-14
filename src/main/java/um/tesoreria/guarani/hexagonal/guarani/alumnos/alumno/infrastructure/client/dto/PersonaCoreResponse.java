package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaCoreResponse {

    private Long uniqueId;
    private BigDecimal personaId;
    private Integer documentoId;
    private String apellido;
    private String nombre;
    private String sexo;
    private Byte primero;
    private String cuit;
    private String cbu;
    private String password;
    private Byte hpum;
    private String numeroPrefijo;
    private String numeroPosfijo;
    private Long guaraniPersona;

}
