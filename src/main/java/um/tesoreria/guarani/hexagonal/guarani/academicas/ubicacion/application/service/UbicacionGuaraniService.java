package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.ports.in.GetAllUbicacionesUseCase;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.ports.in.GetUbicacionGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.ports.in.GetUbicacionesByUbicacionTipoUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UbicacionGuaraniService {

    private final GetUbicacionGuaraniUseCase getUbicacionGuaraniUseCase;
    private final GetAllUbicacionesUseCase getAllUbicacionesUseCase;
    private final GetUbicacionesByUbicacionTipoUseCase getUbicacionesByUbicacionTipoUseCase;

    public UbicacionGuarani getByUbicacionId(Integer ubicacionId) {
        return getUbicacionGuaraniUseCase.getByUbicacionId(ubicacionId);
    }

    public List<UbicacionGuarani> getAllUbicaciones() {
        return getAllUbicacionesUseCase.getAll();
    }

    public List<UbicacionGuarani> getUbicacionesByUbicacionTipo(Integer ubicacionTipo) {
        return getUbicacionesByUbicacionTipoUseCase.getByUbicacionTipo(ubicacionTipo);
    }
}
