package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;

import java.util.List;
import java.util.Optional;

public interface PropuestaAspiraGuaraniRepository {
    Optional<PropuestaAspiraGuarani> findByPropuestaAspiraId(Integer propuestaAspiraId);
    List<PropuestaAspiraGuarani> findAll();
}
