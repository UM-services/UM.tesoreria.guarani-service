package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaOfertaGuarani {
    private Integer propuesta;
    private Integer ubicacion;
    private Integer institucionAraucano;
    private PropuestaGuarani propuestaRel;
}
