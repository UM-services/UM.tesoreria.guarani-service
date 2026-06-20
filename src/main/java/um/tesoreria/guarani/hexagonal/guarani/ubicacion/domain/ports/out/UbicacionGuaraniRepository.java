package um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;

import java.util.List;
import java.util.Optional;

public interface UbicacionGuaraniRepository {
    Optional<UbicacionGuarani> findByUbicacionId(Integer ubicacionId);
    List<UbicacionGuarani> findAll();
}
