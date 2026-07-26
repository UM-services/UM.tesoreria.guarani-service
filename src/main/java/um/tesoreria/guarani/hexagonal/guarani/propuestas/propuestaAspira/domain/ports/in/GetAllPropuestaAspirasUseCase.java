package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;

import java.util.List;

public interface GetAllPropuestaAspirasUseCase {
    List<PropuestaAspiraGuarani> getAll();
}
