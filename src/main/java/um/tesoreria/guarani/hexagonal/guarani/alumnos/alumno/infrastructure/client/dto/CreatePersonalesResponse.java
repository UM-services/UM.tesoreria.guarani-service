package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonalesResponse {

    private Boolean result;
    private AlumnoGuarani alumnoGuarani;
    private PersonaCoreResponse persona;
    private DomicilioCoreResponse domicilio;

}
