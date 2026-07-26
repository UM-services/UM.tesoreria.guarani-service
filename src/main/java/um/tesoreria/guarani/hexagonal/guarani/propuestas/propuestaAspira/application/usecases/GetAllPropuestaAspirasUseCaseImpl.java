package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in.GetAllPropuestaAspirasUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.out.PropuestaAspiraGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllPropuestaAspirasUseCaseImpl implements GetAllPropuestaAspirasUseCase {

    private final PropuestaAspiraGuaraniRepository repository;

    @Override
    public List<PropuestaAspiraGuarani> getAll() {
        return repository.findAll();
    }
}
