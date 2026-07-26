package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaTipo.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaTipo.domain.model.PropuestaTipoGuarani;

public interface GetPropuestaTipoGuaraniUseCase {
    PropuestaTipoGuarani getByPropuestaTipo(Integer propuestaTipo);
}
