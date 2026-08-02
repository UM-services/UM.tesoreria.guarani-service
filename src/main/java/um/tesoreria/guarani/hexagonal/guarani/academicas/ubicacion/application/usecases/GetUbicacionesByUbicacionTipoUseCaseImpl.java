package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.ports.in.GetUbicacionesByUbicacionTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.ports.out.UbicacionGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUbicacionesByUbicacionTipoUseCaseImpl implements GetUbicacionesByUbicacionTipoUseCase {

    private final UbicacionGuaraniRepository repository;

    @Override
    public List<UbicacionGuarani> getByUbicacionTipo(Integer ubicacionTipo) {
        return repository.findAllByUbicacionTipo(ubicacionTipo);
    }
}
