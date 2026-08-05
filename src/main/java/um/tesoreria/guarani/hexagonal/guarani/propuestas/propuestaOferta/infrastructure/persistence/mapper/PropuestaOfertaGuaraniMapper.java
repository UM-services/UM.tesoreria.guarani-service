package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.mapper.PropuestaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model.PropuestaOfertaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.persistence.entity.PropuestaOfertaGuaraniEntity;

@Component
public class PropuestaOfertaGuaraniMapper {
    private final PropuestaGuaraniMapper propuestaMapper;

    public PropuestaOfertaGuaraniMapper(PropuestaGuaraniMapper propuestaMapper) {
        this.propuestaMapper = propuestaMapper;
    }

    public PropuestaOfertaGuarani toDomain(PropuestaOfertaGuaraniEntity entity) {
        if (entity == null) {
            return null;
        }
        return PropuestaOfertaGuarani.builder()
                .propuesta(entity.getPropuesta())
                .ubicacion(entity.getUbicacion())
                .institucionAraucano(entity.getInstitucionAraucano())
                .propuestaRel(propuestaMapper.toDomain(entity.getPropuestaRel()))
                .build();
    }
}
