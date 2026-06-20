package um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.model.PersonaContactoGuarani;

public interface GetPersonaContactoGuaraniUseCase {
    PersonaContactoGuarani getByPersonaContacto(Integer personaContacto);
}
