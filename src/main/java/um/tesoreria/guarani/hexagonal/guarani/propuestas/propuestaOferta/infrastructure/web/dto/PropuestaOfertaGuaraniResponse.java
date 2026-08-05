package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaOfertaGuaraniResponse {
    private Integer propuesta;
    private Integer ubicacion;
    private Integer institucionAraucano;
    private PropuestaGuaraniResponse propuestaRel;
}
