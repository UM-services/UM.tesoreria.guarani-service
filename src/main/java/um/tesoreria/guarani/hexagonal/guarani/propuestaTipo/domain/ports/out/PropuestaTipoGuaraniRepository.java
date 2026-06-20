package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;

import java.util.List;
import java.util.Optional;

public interface PropuestaTipoGuaraniRepository {
    Optional<PropuestaTipoGuarani> findByPropuestaTipo(Integer propuestaTipo);
    List<PropuestaTipoGuarani> findAll();
}
