package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;

import java.util.List;

public interface GetAllRequisitoTiposUseCase {
    List<RequisitoTipoGuarani> getAll();
}
