package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.domain.model.UbicacionTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.web.dto.UbicacionTipoGuaraniResponse;

@Component
public class UbicacionTipoGuaraniDtoMapper {

    public UbicacionTipoGuaraniResponse toResponse(UbicacionTipoGuarani domain) {
        if (domain == null) {
            return null;
        }

        return UbicacionTipoGuaraniResponse.builder()
                .ubicacionTipo(domain.getUbicacionTipo())
                .nombre(domain.getNombre())
                .descripcion(domain.getDescripcion())
                .build();
    }
}
