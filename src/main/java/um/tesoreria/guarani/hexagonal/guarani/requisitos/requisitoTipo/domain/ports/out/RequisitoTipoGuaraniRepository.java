package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;

import java.util.List;
import java.util.Optional;

public interface RequisitoTipoGuaraniRepository {
    Optional<RequisitoTipoGuarani> findByRequisitoTipo(Integer requisitoTipo);
    List<RequisitoTipoGuarani> findAll();
}
