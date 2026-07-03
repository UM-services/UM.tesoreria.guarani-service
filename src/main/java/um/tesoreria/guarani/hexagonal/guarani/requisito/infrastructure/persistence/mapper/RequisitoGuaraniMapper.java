package um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.model.RequisitoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.persistence.entity.RequisitoGuaraniEntity;

@Component
public class RequisitoGuaraniMapper {

    public RequisitoGuaraniEntity toEntity(RequisitoGuarani domain) {
        if (domain == null) return null;
        return RequisitoGuaraniEntity.builder()
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

    public RequisitoGuarani toDomain(RequisitoGuaraniEntity entity) {
        if (entity == null) return null;
        return RequisitoGuarani.builder()
                .requisito(entity.getRequisito())
                .nombre(entity.getNombre())
                .requisitoTipo(entity.getRequisitoTipo())
                .requisitoSubordinadoDe(entity.getRequisitoSubordinadoDe())
                .descripcion(entity.getDescripcion())
                .regla(entity.getRegla())
                .cacheable(entity.getCacheable())
                .requisitoIngreso(entity.getRequisitoIngreso())
                .activo(entity.getActivo())
                .tipoDocumentoDigital(entity.getTipoDocumentoDigital())
                .requisitoEgreso(entity.getRequisitoEgreso())
                .aplicaABecas(entity.getAplicaABecas())
                .build();
    }
}
