package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.model.UbicacionGuarani;

import java.util.List;

public interface GetAllUbicacionesUseCase {
    List<UbicacionGuarani> getAll();
}
