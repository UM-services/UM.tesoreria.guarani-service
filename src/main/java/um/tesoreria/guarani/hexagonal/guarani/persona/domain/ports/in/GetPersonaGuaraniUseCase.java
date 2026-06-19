package um.tesoreria.guarani.hexagonal.guarani.persona.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.persona.domain.model.PersonaGuarani;

public interface GetPersonaGuaraniUseCase {
    PersonaGuarani getByPersonaId(Integer personaId);
}
