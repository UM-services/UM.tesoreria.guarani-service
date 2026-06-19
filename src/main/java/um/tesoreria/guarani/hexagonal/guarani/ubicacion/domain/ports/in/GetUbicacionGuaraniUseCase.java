package um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;

public interface GetUbicacionGuaraniUseCase {
    UbicacionGuarani getByUbicacionId(Integer ubicacionId);
}
