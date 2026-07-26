package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.exception.PropuestaAspiraGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in.GetPropuestaAspiraGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.out.PropuestaAspiraGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetPropuestaAspiraGuaraniUseCaseImpl implements GetPropuestaAspiraGuaraniUseCase {

    private final PropuestaAspiraGuaraniRepository repository;

    @Override
    public PropuestaAspiraGuarani getByPropuestaAspiraId(Integer propuestaAspiraId) {
        return repository.findByPropuestaAspiraId(propuestaAspiraId)
                .orElseThrow(() -> new PropuestaAspiraGuaraniException(propuestaAspiraId));
    }
}
