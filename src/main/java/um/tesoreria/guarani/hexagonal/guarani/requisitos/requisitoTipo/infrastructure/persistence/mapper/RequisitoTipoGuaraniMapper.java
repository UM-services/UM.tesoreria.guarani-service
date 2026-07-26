package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.persistence.entity.RequisitoTipoGuaraniEntity;

@Component
public class RequisitoTipoGuaraniMapper {

    public RequisitoTipoGuaraniEntity toEntity(RequisitoTipoGuarani domain) {
        if (domain == null) return null;
        return RequisitoTipoGuaraniEntity.builder()
                .requisitoTipo(domain.getRequisitoTipo())
                .nombre(domain.getNombre())
                .descripcion(domain.getDescripcion())
                .regla(domain.getRegla())
                .publico(domain.getPublico())
                .icono(domain.getIcono())
                .build();
    }

    public RequisitoTipoGuarani toDomain(RequisitoTipoGuaraniEntity entity) {
        if (entity == null) return null;
        return RequisitoTipoGuarani.builder()
                .requisitoTipo(entity.getRequisitoTipo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .regla(entity.getRegla())
                .publico(entity.getPublico())
                .icono(entity.getIcono())
                .build();
    }
}
