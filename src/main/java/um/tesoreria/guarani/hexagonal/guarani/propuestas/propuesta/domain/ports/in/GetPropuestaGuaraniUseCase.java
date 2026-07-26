package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;

public interface GetPropuestaGuaraniUseCase {
    PropuestaGuarani getByPropuestaId(Integer propuestaId);
}
