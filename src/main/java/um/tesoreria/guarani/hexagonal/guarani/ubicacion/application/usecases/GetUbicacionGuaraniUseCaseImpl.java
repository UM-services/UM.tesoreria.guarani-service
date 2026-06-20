package um.tesoreria.guarani.hexagonal.guarani.ubicacion.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.application.exception.UbicacionGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.ports.in.GetUbicacionGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.ports.out.UbicacionGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetUbicacionGuaraniUseCaseImpl implements GetUbicacionGuaraniUseCase {

    private final UbicacionGuaraniRepository repository;

    @Override
    public UbicacionGuarani getByUbicacionId(Integer ubicacionId) {
        return repository.findByUbicacionId(ubicacionId).orElseThrow(() -> new UbicacionGuaraniException(ubicacionId));
    }

}
