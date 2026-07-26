package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.ports.in.GetAllPropuestasUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.domain.ports.out.PropuestaGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllPropuestasUseCaseImpl implements GetAllPropuestasUseCase {

    private final PropuestaGuaraniRepository repository;

    @Override
    public List<PropuestaGuarani> getAll() {
        return repository.findAll();
    }

}
