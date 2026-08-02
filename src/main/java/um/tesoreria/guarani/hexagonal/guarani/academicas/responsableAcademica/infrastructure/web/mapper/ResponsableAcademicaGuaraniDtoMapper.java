package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.model.ResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.web.dto.ResponsableAcademicaGuaraniResponse;

@Component
public class ResponsableAcademicaGuaraniDtoMapper {

    public ResponsableAcademicaGuaraniResponse toResponse(ResponsableAcademicaGuarani domain) {
        if (domain == null) {
            return null;
        }

        return ResponsableAcademicaGuaraniResponse.builder()
                .responsableAcademica(domain.getResponsableAcademica())
                .nombre(domain.getNombre())
                .codigo(domain.getCodigo())
                .responsableAcademicaTipo(domain.getResponsableAcademicaTipo())
                .institucion(domain.getInstitucion())
                .institucionAraucano(domain.getInstitucionAraucano())
                .calle(domain.getCalle())
                .numero(domain.getNumero())
                .localidad(domain.getLocalidad())
                .codigoPostal(domain.getCodigoPostal())
                .telefono(domain.getTelefono())
                .fax(domain.getFax())
                .email(domain.getEmail())
                .entidad(domain.getEntidad())
                .build();
    }
}
