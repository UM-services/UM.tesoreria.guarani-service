package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.web.dto.RequisitoTipoGuaraniResponse;

@Component
public class RequisitoTipoGuaraniDtoMapper {

    public RequisitoTipoGuaraniResponse toResponse(RequisitoTipoGuarani domain) {
        if (domain == null) return null;
        return RequisitoTipoGuaraniResponse.builder()
                .requisitoTipo(domain.getRequisitoTipo())
                .nombre(domain.getNombre())
                .descripcion(domain.getDescripcion())
                .regla(domain.getRegla())
                .publico(domain.getPublico())
                .icono(domain.getIcono())
                .build();
    }
}
