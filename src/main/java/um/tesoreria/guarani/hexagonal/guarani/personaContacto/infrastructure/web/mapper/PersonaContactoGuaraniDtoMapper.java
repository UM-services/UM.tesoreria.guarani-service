package um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.model.PersonaContactoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.web.dto.PersonaContactoGuaraniResponse;

@Component
public class PersonaContactoGuaraniDtoMapper {

    public PersonaContactoGuaraniResponse toResponse(PersonaContactoGuarani domain) {
        if (domain == null) return null;
        return PersonaContactoGuaraniResponse.builder()
                .personaContacto(domain.getPersonaContacto())
                .persona(domain.getPersona())
                .contactoTipo(domain.getContactoTipo())
                .otrosContactos(domain.getOtrosContactos())
                .email(domain.getEmail())
                .telefonoCodigoArea(domain.getTelefonoCodigoArea())
                .telefonoNumero(domain.getTelefonoNumero())
                .build();
    }
}
