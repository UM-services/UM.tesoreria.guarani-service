package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model.UbicacionTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.ports.in.GetAllUbicacionTiposUseCase;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.ports.out.UbicacionTipoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllUbicacionTiposUseCaseImpl implements GetAllUbicacionTiposUseCase {

    private final UbicacionTipoGuaraniRepository repository;

    @Override
    public List<UbicacionTipoGuarani> getAll() {
        return repository.findAll();
    }
}
