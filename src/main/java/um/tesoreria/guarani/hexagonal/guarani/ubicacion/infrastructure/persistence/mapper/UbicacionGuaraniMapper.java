package um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.domain.model.UbicacionGuarani;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.entity.UbicacionGuaraniEntity;

@Component
public class UbicacionGuaraniMapper {

    public UbicacionGuaraniEntity toEntity(UbicacionGuarani domain) {
        if (domain == null) return null;
        return UbicacionGuaraniEntity.builder()
                .ubicacion(domain.getUbicacionId())
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

    public UbicacionGuarani toDomain(UbicacionGuaraniEntity entity) {
        if (entity == null) return null;
        return UbicacionGuarani.builder()
                .ubicacionId(entity.getUbicacion())
                .nombre(entity.getNombre())
                .ubicacionTipo(entity.getUbicacionTipo())
                .localidad(entity.getLocalidad())
                .calle(entity.getCalle())
                .numero(entity.getNumero())
                .codigoPostal(entity.getCodigoPostal())
                .telefono(entity.getTelefono())
                .fax(entity.getFax())
                .email(entity.getEmail())
                .institucionAraucano(entity.getInstitucionAraucano())
                .latitud(entity.getLatitud())
                .longitud(entity.getLongitud())
                .build();
    }
}
