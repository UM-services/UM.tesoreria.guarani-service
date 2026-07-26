package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;

public interface GetRequisitoPresentadoGuaraniUseCase {
    RequisitoPresentadoGuarani getByRequisitoPresentadoId(Integer requisitoPresentadoId);
}
