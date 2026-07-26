package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.domain.model.PersonaContactoGuarani;

public interface GetPersonaContactoGuaraniUseCase {
    PersonaContactoGuarani getByPersonaContacto(Integer personaContacto);
}
