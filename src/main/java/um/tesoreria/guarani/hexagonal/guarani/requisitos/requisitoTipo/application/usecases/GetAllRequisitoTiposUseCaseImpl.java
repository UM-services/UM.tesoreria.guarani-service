package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.in.GetAllRequisitoTiposUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.out.RequisitoTipoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllRequisitoTiposUseCaseImpl implements GetAllRequisitoTiposUseCase {

    private final RequisitoTipoGuaraniRepository repository;

    @Override
    public List<RequisitoTipoGuarani> getAll() {
        return repository.findAll();
    }

}
