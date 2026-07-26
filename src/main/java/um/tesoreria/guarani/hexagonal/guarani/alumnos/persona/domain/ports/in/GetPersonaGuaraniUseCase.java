package um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;

public interface GetPersonaGuaraniUseCase {
    PersonaGuarani getByPersonaId(Integer personaId);
}
