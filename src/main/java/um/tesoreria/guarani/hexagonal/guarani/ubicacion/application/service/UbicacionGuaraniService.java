package um.tesoreria.guarani.hexagonal.guarani.ubicacion.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.ports.in.GetUbicacionGuaraniUseCase;

@Service
@RequiredArgsConstructor
public class UbicacionGuaraniService {

    private final GetUbicacionGuaraniUseCase getUbicacionGuaraniUseCase;

    public UbicacionGuarani getByUbicacionId(Integer ubicacionId) {
        return getUbicacionGuaraniUseCase.getByUbicacionId(ubicacionId);
    }
}
