package um.tesoreria.guarani.hexagonal.guarani.requisito.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisito.application.exception.RequisitoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.model.RequisitoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.ports.in.GetRequisitoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.ports.out.RequisitoGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetRequisitoGuaraniUseCaseImpl implements GetRequisitoGuaraniUseCase {

    private final RequisitoGuaraniRepository repository;

    @Override
    public RequisitoGuarani getByRequisitoId(Integer requisitoId) {
        return repository.findByRequisitoId(requisitoId).orElseThrow(() -> new RequisitoGuaraniException(requisitoId));
    }

}
