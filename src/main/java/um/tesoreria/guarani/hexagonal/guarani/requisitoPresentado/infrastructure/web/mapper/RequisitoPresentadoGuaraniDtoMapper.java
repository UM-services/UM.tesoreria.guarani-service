package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.web.mapper.RequisitoGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.web.dto.RequisitoPresentadoGuaraniResponse;

@Component
@RequiredArgsConstructor
public class RequisitoPresentadoGuaraniDtoMapper {

    private final RequisitoGuaraniDtoMapper requisitoGuaraniDtoMapper;

    public RequisitoPresentadoGuaraniResponse toResponse(RequisitoPresentadoGuarani domain) {
        if (domain == null) return null;
        return RequisitoPresentadoGuaraniResponse.builder()
                .requisitoPresentado(domain.getRequisitoPresentado())
                .persona(domain.getPersona())
                .requisito(domain.getRequisito())
                .fechaPresentacion(domain.getFechaPresentacion())
                .fechaVencimiento(domain.getFechaVencimiento())
                .fechaAlta(domain.getFechaAlta())
                .requisitoDigital(domain.getRequisitoDigital())
                .observaciones(domain.getObservaciones())
                .requisitoRel(requisitoGuaraniDtoMapper.toResponse(domain.getRequisitoRel()))
                .build();
    }
}
