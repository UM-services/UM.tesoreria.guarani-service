package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;

import java.util.List;
import java.util.Optional;

public interface RequisitoPresentadoRepository {
    Optional<RequisitoPresentadoGuarani> findByRequisitoPresentadoId(Integer requisitoPresentadoId);
    List<RequisitoPresentadoGuarani> findAll();
}
