package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;

import java.util.List;
import java.util.Optional;

public interface PropuestaGuaraniRepository {
    Optional<PropuestaGuarani> findByPropuestaId(Integer propuestaId);
    List<PropuestaGuarani> findAll();
}
