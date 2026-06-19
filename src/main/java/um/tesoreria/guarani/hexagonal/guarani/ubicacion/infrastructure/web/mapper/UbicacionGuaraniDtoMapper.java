package um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.web.dto.UbicacionGuaraniResponse;

@Component
public class UbicacionGuaraniDtoMapper {

    public UbicacionGuaraniResponse toResponse(UbicacionGuarani domain) {
        if (domain == null) return null;
        return UbicacionGuaraniResponse.builder()
                .ubicacionId(domain.getUbicacionId())
                .nombre(domain.getNombre())
                .ubicacionTipo(domain.getUbicacionTipo())
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
