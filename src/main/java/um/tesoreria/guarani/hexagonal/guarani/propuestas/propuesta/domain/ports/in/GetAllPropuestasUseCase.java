package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;

import java.util.List;

public interface GetAllPropuestasUseCase {
    List<PropuestaGuarani> getAll();
}
