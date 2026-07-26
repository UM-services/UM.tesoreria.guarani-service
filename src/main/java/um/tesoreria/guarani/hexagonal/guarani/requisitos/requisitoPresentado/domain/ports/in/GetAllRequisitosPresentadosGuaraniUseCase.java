package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;

import java.util.List;

public interface GetAllRequisitosPresentadosGuaraniUseCase {
    List<RequisitoPresentadoGuarani> getAll();
}
