package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.model.UbicacionGuarani;

public interface GetUbicacionGuaraniUseCase {
    UbicacionGuarani getByUbicacionId(Integer ubicacionId);
}
