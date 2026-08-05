package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.out;

import java.util.List;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model.PropuestaOfertaGuarani;

public interface PropuestaOfertaGuaraniRepository {
    List<PropuestaOfertaGuarani> findAllByUbicacion(Integer ubicacion);

    List<PropuestaOfertaGuarani> findAllByUbicacionAndPropuestaTipo(Integer ubicacion, Integer propuestaTipo);
}
