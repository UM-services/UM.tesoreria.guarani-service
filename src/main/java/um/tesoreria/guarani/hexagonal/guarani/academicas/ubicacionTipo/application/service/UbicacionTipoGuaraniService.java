package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model.UbicacionTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.ports.in.GetAllUbicacionTiposUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UbicacionTipoGuaraniService {

    private final GetAllUbicacionTiposUseCase getAllUbicacionTiposUseCase;

    public List<UbicacionTipoGuarani> getAllUbicacionTipos() {
        return getAllUbicacionTiposUseCase.getAll();
    }
}
