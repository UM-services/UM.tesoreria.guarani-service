package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;

public interface GetPropuestaAspiraGuaraniUseCase {
    PropuestaAspiraGuarani getByPropuestaAspiraId(Integer propuestaAspiraId);
}
