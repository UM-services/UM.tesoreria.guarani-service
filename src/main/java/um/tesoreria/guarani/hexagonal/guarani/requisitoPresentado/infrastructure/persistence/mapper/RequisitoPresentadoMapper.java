package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.persistence.mapper.RequisitoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.persistence.entity.RequisitoPresentadoGuaraniEntity;

@Component
@RequiredArgsConstructor
public class RequisitoPresentadoMapper {

    private final RequisitoGuaraniMapper requisitoGuaraniMapper;

    public RequisitoPresentadoGuaraniEntity toEntity(RequisitoPresentadoGuarani domain) {
        if (domain == null) return null;
        return RequisitoPresentadoGuaraniEntity.builder()
                .requisitoPresentado(domain.getRequisitoPresentado())
                .persona(domain.getPersona())
                .requisito(domain.getRequisito())
                .fechaPresentacion(domain.getFechaPresentacion())
                .fechaVencimiento(domain.getFechaVencimiento())
                .fechaAlta(domain.getFechaAlta())
                .requisitoDigital(domain.getRequisitoDigital())
                .observaciones(domain.getObservaciones())
                .requisitoRel(requisitoGuaraniMapper.toEntity(domain.getRequisitoRel()))
                .build();
    }

    public RequisitoPresentadoGuarani toDomain(RequisitoPresentadoGuaraniEntity entity) {
        if (entity == null) return null;
        return RequisitoPresentadoGuarani.builder()
                .requisitoPresentado(entity.getRequisitoPresentado())
                .persona(entity.getPersona())
                .requisito(entity.getRequisito())
                .fechaPresentacion(entity.getFechaPresentacion())
                .fechaVencimiento(entity.getFechaVencimiento())
                .fechaAlta(entity.getFechaAlta())
                .requisitoDigital(entity.getRequisitoDigital())
                .observaciones(entity.getObservaciones())
                .requisitoRel(requisitoGuaraniMapper.toDomain(entity.getRequisitoRel()))
                .build();
    }
}
