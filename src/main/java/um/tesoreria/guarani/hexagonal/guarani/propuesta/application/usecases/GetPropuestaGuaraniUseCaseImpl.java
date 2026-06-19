package um.tesoreria.guarani.hexagonal.guarani.propuesta.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.application.exception.PropuestaGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.ports.in.GetPropuestaGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.ports.out.PropuestaGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetPropuestaGuaraniUseCaseImpl implements GetPropuestaGuaraniUseCase {

    private final PropuestaGuaraniRepository repository;

    @Override
    public PropuestaGuarani getByPropuestaId(Integer propuestaId) {
        return repository.findByPropuestaId(propuestaId).orElseThrow(() -> new PropuestaGuaraniException(propuestaId));
    }
}
