package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.web.mapper.PropuestaGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model.PropuestaOfertaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.web.dto.PropuestaOfertaGuaraniResponse;

@Component
public class PropuestaOfertaGuaraniDtoMapper {
    private final PropuestaGuaraniDtoMapper propuestaMapper;

    public PropuestaOfertaGuaraniDtoMapper(PropuestaGuaraniDtoMapper propuestaMapper) {
        this.propuestaMapper = propuestaMapper;
    }

    public PropuestaOfertaGuaraniResponse toResponse(PropuestaOfertaGuarani domain) {
        if (domain == null) {
            return null;
        }
        return PropuestaOfertaGuaraniResponse.builder()
                .propuesta(domain.getPropuesta())
                .ubicacion(domain.getUbicacion())
                .institucionAraucano(domain.getInstitucionAraucano())
                .propuestaRel(propuestaMapper.toResponse(domain.getPropuestaRel()))
                .build();
    }
}
