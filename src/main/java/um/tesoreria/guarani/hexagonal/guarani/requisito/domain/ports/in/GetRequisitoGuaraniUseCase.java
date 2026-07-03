package um.tesoreria.guarani.hexagonal.guarani.requisito.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.model.RequisitoGuarani;

public interface GetRequisitoGuaraniUseCase {
    RequisitoGuarani getByRequisitoId(Integer requisitoId);
}
