package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.ports.in.GetAllRequisitosPresentadosGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.ports.out.RequisitoPresentadoRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllRequisitosPresentadosGuaraniUseCaseImpl implements GetAllRequisitosPresentadosGuaraniUseCase {

    private final RequisitoPresentadoRepository repository;

    @Override
    public List<RequisitoPresentadoGuarani> getAll() {
        return repository.findAll();
    }
}
