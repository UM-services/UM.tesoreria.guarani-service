package um.tesoreria.guarani.hexagonal.guarani.propuestaAspira.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestaAspira.domain.model.PropuestaAspiraGuarani;

public interface GetPropuestaAspiraGuaraniUseCase {
    PropuestaAspiraGuarani getByPropuestaAspiraId(Integer propuestaAspiraId);
}
