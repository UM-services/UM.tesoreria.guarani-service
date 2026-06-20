package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;

public interface GetPropuestaTipoGuaraniUseCase {
    PropuestaTipoGuarani getByPropuestaTipo(Integer propuestaTipo);
}
