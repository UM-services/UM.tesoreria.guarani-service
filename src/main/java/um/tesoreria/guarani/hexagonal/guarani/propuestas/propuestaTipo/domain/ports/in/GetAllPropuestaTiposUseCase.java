package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaTipo.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaTipo.domain.model.PropuestaTipoGuarani;

import java.util.List;

public interface GetAllPropuestaTiposUseCase {
    List<PropuestaTipoGuarani> getAll();
}
