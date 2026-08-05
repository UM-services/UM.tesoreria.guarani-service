package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.in;

import java.util.List;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model.PropuestaOfertaGuarani;

public interface GetPropuestaOfertasByUbicacionUseCase {
    List<PropuestaOfertaGuarani> getByUbicacion(Integer ubicacion);
}
