package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.in.GetAllPropuestaTiposUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.out.PropuestaTipoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllPropuestaTiposUseCaseImpl implements GetAllPropuestaTiposUseCase {

    private final PropuestaTipoGuaraniRepository repository;

    @Override
    public List<PropuestaTipoGuarani> getAll() {
        return repository.findAll();
    }

}
