package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDeteccionRequest {

    private Integer ubicacion;
    private Integer propuesta;
    private String nroDocumento;
    private Short tipoDocumento;
    private Boolean pendiente;

}
