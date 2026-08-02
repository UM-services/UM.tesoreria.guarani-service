package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacion.infrastructure.web.dto.UbicacionGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.web.mapper.UbicacionTipoGuaraniDtoMapper;

@Component
public class UbicacionGuaraniDtoMapper {

    private final UbicacionTipoGuaraniDtoMapper ubicacionTipoDtoMapper;

    public UbicacionGuaraniDtoMapper(UbicacionTipoGuaraniDtoMapper ubicacionTipoDtoMapper) {
        this.ubicacionTipoDtoMapper = ubicacionTipoDtoMapper;
    }

    public UbicacionGuaraniResponse toResponse(UbicacionGuarani domain) {
        if (domain == null) return null;
        return UbicacionGuaraniResponse.builder()
                .ubicacion(domain.getUbicacion())
                .nombre(domain.getNombre())
                .ubicacionTipo(domain.getUbicacionTipo())
                .ubicacionTipoRel(ubicacionTipoDtoMapper.toResponse(domain.getUbicacionTipoRel()))
                .localidad(domain.getLocalidad())
                .calle(domain.getCalle())
                .numero(domain.getNumero())
                .codigoPostal(domain.getCodigoPostal())
                .telefono(domain.getTelefono())
                .fax(domain.getFax())
                .email(domain.getEmail())
                .institucionAraucano(domain.getInstitucionAraucano())
                .latitud(domain.getLatitud())
                .longitud(domain.getLongitud())
                .build();
    }
}
