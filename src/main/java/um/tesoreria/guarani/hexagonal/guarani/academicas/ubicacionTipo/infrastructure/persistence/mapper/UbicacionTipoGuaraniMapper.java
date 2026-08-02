package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model.UbicacionTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.persistence.entity.UbicacionTipoGuaraniEntity;

@Component
public class UbicacionTipoGuaraniMapper {

    public UbicacionTipoGuaraniEntity toEntity(UbicacionTipoGuarani domain) {
        if (domain == null) {
            return null;
        }

        return UbicacionTipoGuaraniEntity.builder()
                .ubicacionTipo(domain.getUbicacionTipo())
                .nombre(domain.getNombre())
                .descripcion(domain.getDescripcion())
                .build();
    }

    public UbicacionTipoGuarani toDomain(UbicacionTipoGuaraniEntity entity) {
        if (entity == null) {
            return null;
        }

        return UbicacionTipoGuarani.builder()
                .ubicacionTipo(entity.getUbicacionTipo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
