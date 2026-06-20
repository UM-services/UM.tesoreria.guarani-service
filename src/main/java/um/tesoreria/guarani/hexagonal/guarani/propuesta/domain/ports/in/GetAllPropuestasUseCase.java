package um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;

import java.util.List;

public interface GetAllPropuestasUseCase {
    List<PropuestaGuarani> getAll();
}
