package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.model.RequisitoGuarani;

import java.util.List;

public interface GetAllRequisitosUseCase {
    List<RequisitoGuarani> getAll();
}
