package um.tesoreria.guarani.hexagonal.guarani.requisito.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.model.RequisitoGuarani;

import java.util.List;
import java.util.Optional;

public interface RequisitoGuaraniRepository {
    Optional<RequisitoGuarani> findByRequisitoId(Integer requisitoId);
    List<RequisitoGuarani> findAll();
}
