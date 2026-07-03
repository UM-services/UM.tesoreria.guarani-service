package um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.model.RequisitoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.web.dto.RequisitoGuaraniResponse;

@Component
public class RequisitoGuaraniDtoMapper {

    public RequisitoGuaraniResponse toResponse(RequisitoGuarani domain) {
        if (domain == null) return null;
        return RequisitoGuaraniResponse.builder()
                .requisito(domain.getRequisito())
                .nombre(domain.getNombre())
                .requisitoTipo(domain.getRequisitoTipo())
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
