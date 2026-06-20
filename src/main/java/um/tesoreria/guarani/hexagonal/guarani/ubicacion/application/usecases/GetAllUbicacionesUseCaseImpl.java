package um.tesoreria.guarani.hexagonal.guarani.ubicacion.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.ports.in.GetAllUbicacionesUseCase;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.ports.out.UbicacionGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllUbicacionesUseCaseImpl implements GetAllUbicacionesUseCase {

    private final UbicacionGuaraniRepository repository;

    @Override
    public List<UbicacionGuarani> getAll() {
        return repository.findAll();
    }

}
