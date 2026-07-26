package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.application.exception.RequisitoTipoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.in.GetRequisitoTipoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.out.RequisitoTipoGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetRequisitoTipoGuaraniUseCaseImpl implements GetRequisitoTipoGuaraniUseCase {

    private final RequisitoTipoGuaraniRepository repository;

    @Override
    public RequisitoTipoGuarani getByRequisitoTipo(Integer requisitoTipo) {
        return repository.findByRequisitoTipo(requisitoTipo).orElseThrow(() -> new RequisitoTipoGuaraniException(requisitoTipo));
    }

}
