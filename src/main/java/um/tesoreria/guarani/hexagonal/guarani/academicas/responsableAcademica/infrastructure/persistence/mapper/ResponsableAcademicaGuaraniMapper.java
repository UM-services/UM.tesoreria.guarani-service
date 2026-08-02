package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.model.ResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.entity.ResponsableAcademicaGuaraniEntity;

@Component
public class ResponsableAcademicaGuaraniMapper {

    public ResponsableAcademicaGuarani toDomain(ResponsableAcademicaGuaraniEntity entity) {
        if (entity == null) {
            return null;
        }

        return ResponsableAcademicaGuarani.builder()
                .responsableAcademica(entity.getResponsableAcademica())
                .nombre(entity.getNombre())
                .codigo(entity.getCodigo())
                .responsableAcademicaTipo(entity.getResponsableAcademicaTipo())
                .institucion(entity.getInstitucion())
                .institucionAraucano(entity.getInstitucionAraucano())
                .calle(entity.getCalle())
                .numero(entity.getNumero())
                .localidad(entity.getLocalidad())
                .codigoPostal(entity.getCodigoPostal())
                .telefono(entity.getTelefono())
                .fax(entity.getFax())
                .email(entity.getEmail())
                .entidad(entity.getEntidad())
                .build();
    }
}
