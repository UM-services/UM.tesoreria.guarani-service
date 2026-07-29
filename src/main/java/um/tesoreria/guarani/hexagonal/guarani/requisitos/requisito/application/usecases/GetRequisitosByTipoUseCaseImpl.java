package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.model.RequisitoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.in.GetRequisitosByTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.out.RequisitoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetRequisitosByTipoUseCaseImpl implements GetRequisitosByTipoUseCase {

    private final RequisitoGuaraniRepository repository;

    @Override
    public List<RequisitoGuarani> getByRequisitoTipo(Integer requisitoTipo) {
        return repository.findByRequisitoTipo(requisitoTipo);
    }
}
