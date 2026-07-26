package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.domain.ports.in.GetAllRequisitosPresentadosGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.domain.ports.in.GetRequisitoPresentadoGuaraniUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoPresentadoGuaraniService {

    private final GetRequisitoPresentadoGuaraniUseCase getRequisitoPresentadoUseCase;
    private final GetAllRequisitosPresentadosGuaraniUseCase getAllRequisitosPresentadosUseCase;

    public RequisitoPresentadoGuarani getByRequisitoPresentadoId(Integer requisitoPresentadoId) {
        return getRequisitoPresentadoUseCase.getByRequisitoPresentadoId(requisitoPresentadoId);
    }

    public List<RequisitoPresentadoGuarani> getAllRequisitosPresentados() {
        return getAllRequisitosPresentadosUseCase.getAll();
    }
}
