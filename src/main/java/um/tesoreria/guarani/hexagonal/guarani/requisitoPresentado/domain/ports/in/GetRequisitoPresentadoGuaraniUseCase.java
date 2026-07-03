package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;

public interface GetRequisitoPresentadoGuaraniUseCase {
    RequisitoPresentadoGuarani getByRequisitoPresentadoId(Integer requisitoPresentadoId);
}
