package um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.model.PersonaContactoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.entity.PersonaContactoGuaraniEntity;

@Component
public class PersonaContactoGuaraniMapper {

    public PersonaContactoGuaraniEntity toEntity(PersonaContactoGuarani domain) {
        if (domain == null) return null;
        return PersonaContactoGuaraniEntity.builder()
                .personaContacto(domain.getPersonaContacto())
                .persona(domain.getPersona())
                .contactoTipo(domain.getContactoTipo())
                .otrosContactos(domain.getOtrosContactos())
                .email(domain.getEmail())
                .telefonoCodigoArea(domain.getTelefonoCodigoArea())
                .telefonoNumero(domain.getTelefonoNumero())
                .build();
    }

    public PersonaContactoGuarani toDomain(PersonaContactoGuaraniEntity entity) {
        if (entity == null) return null;
        return PersonaContactoGuarani.builder()
                .personaContacto(entity.getPersonaContacto())
                .persona(entity.getPersona())
                .contactoTipo(entity.getContactoTipo())
                .otrosContactos(entity.getOtrosContactos())
                .email(entity.getEmail())
                .telefonoCodigoArea(entity.getTelefonoCodigoArea())
                .telefonoNumero(entity.getTelefonoNumero())
                .build();
    }
}
