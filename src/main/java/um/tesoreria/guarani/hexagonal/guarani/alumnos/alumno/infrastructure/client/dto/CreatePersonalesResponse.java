package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonalesResponse {

    private Boolean result;
    private AlumnoGuarani alumnoGuarani;
    private PropuestaGuaraniResponse propuestaGuarani;
    private PersonaCoreResponse persona;
    private DomicilioCoreResponse domicilio;

    @JsonIgnore
    public boolean esCreado() {
        return Boolean.TRUE.equals(result) && persona != null;
    }

}
