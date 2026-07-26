package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;

public interface GetRequisitoTipoGuaraniUseCase {
    RequisitoTipoGuarani getByRequisitoTipo(Integer requisitoTipo);
}
