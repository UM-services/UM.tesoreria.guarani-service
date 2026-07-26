package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.model.RequisitoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.web.dto.RequisitoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.web.mapper.RequisitoTipoGuaraniDtoMapper;

@Component
public class RequisitoGuaraniDtoMapper {

    private final RequisitoTipoGuaraniDtoMapper requisitoTipoDtoMapper;

    public RequisitoGuaraniDtoMapper(RequisitoTipoGuaraniDtoMapper requisitoTipoDtoMapper) {
        this.requisitoTipoDtoMapper = requisitoTipoDtoMapper;
    }

    public RequisitoGuaraniResponse toResponse(RequisitoGuarani domain) {
        if (domain == null) return null;
        return RequisitoGuaraniResponse.builder()
                .requisito(domain.getRequisito())
                .nombre(domain.getNombre())
                .requisitoTipo(domain.getRequisitoTipo())
                .requisitoTipoRel(requisitoTipoDtoMapper.toResponse(domain.getRequisitoTipoRel()))
                .requisitoSubordinadoDe(domain.getRequisitoSubordinadoDe())
                .descripcion(domain.getDescripcion())
                .regla(domain.getRegla())
                .cacheable(domain.getCacheable())
                .requisitoIngreso(domain.getRequisitoIngreso())
                .activo(domain.getActivo())
                .tipoDocumentoDigital(domain.getTipoDocumentoDigital())
                .requisitoEgreso(domain.getRequisitoEgreso())
                .aplicaABecas(domain.getAplicaABecas())
                .build();
    }
}
