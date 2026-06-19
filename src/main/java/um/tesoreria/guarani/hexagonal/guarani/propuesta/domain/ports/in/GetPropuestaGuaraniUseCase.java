package um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;

public interface GetPropuestaGuaraniUseCase {
    PropuestaGuarani getByPropuestaId(Integer propuestaId);
}
