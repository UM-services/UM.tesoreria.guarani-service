package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.application.exception.RequisitoPresentadoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.ports.in.GetRequisitoPresentadoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.ports.out.RequisitoPresentadoRepository;

@Component
@RequiredArgsConstructor
public class GetRequisitoPresentadoGuaraniUseCaseImpl implements GetRequisitoPresentadoGuaraniUseCase {

    private final RequisitoPresentadoRepository repository;

    @Override
    public RequisitoPresentadoGuarani getByRequisitoPresentadoId(Integer requisitoPresentadoId) {
        return repository.findByRequisitoPresentadoId(requisitoPresentadoId).orElseThrow(() -> new RequisitoPresentadoGuaraniException(requisitoPresentadoId));
    }
}
