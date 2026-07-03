package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.ports.out.RequisitoPresentadoRepository;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.persistence.mapper.RequisitoPresentadoMapper;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.persistence.repository.JpaRequisitoPresentadoGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRequisitoPresentadoGuaraniRepositoryAdapter implements RequisitoPresentadoRepository {

    private final JpaRequisitoPresentadoGuaraniRepository jpaRequisitoPresentadoRepository;
    private final RequisitoPresentadoMapper mapper;

    @Override
    public Optional<RequisitoPresentadoGuarani> findByRequisitoPresentadoId(Integer requisitoPresentadoId) {
        return jpaRequisitoPresentadoRepository.findById(requisitoPresentadoId)
                .map(mapper::toDomain);
    }

    @Override
    public List<RequisitoPresentadoGuarani> findAll() {
        return jpaRequisitoPresentadoRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
