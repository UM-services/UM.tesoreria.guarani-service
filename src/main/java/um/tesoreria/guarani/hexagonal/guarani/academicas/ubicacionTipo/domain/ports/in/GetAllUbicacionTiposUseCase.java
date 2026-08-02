package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model.UbicacionTipoGuarani;

import java.util.List;

public interface GetAllUbicacionTiposUseCase {

    List<UbicacionTipoGuarani> getAll();
}
