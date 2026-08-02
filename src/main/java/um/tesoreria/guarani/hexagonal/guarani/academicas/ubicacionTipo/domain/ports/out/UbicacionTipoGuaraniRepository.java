package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model.UbicacionTipoGuarani;

import java.util.List;

public interface UbicacionTipoGuaraniRepository {

    List<UbicacionTipoGuarani> findAll();
}
