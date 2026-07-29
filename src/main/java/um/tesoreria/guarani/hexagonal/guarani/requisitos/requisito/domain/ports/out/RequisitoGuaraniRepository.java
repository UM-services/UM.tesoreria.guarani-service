package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.model.RequisitoGuarani;

import java.util.List;
import java.util.Optional;

public interface RequisitoGuaraniRepository {
    Optional<RequisitoGuarani> findByRequisitoId(Integer requisitoId);
    List<RequisitoGuarani> findAll();
    List<RequisitoGuarani> findByRequisitoTipo(Integer requisitoTipo);
}
