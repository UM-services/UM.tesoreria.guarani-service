package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.model.RequisitoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.in.GetAllRequisitosUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.out.RequisitoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllRequisitosUseCaseImpl implements GetAllRequisitosUseCase {

    private final RequisitoGuaraniRepository repository;

    @Override
    public List<RequisitoGuarani> getAll() {
        return repository.findAll();
    }

}
