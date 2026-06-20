package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.application.exception.PropuestaTipoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.in.GetPropuestaTipoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.out.PropuestaTipoGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetPropuestaTipoGuaraniUseCaseImpl implements GetPropuestaTipoGuaraniUseCase {

    private final PropuestaTipoGuaraniRepository repository;

    @Override
    public PropuestaTipoGuarani getByPropuestaTipo(Integer propuestaTipo) {
        return repository.findByPropuestaTipo(propuestaTipo).orElseThrow(() -> new PropuestaTipoGuaraniException(propuestaTipo));
    }

}
