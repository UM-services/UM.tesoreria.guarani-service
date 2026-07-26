package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.model.RequisitoGuarani;

public interface GetRequisitoGuaraniUseCase {
    RequisitoGuarani getByRequisitoId(Integer requisitoId);
}
