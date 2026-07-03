package um.tesoreria.guarani.hexagonal.guarani.requisito.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.model.RequisitoGuarani;

import java.util.List;

public interface GetAllRequisitosUseCase {
    List<RequisitoGuarani> getAll();
}
