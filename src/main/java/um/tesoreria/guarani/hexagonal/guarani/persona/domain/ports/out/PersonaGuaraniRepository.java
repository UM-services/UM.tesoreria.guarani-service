package um.tesoreria.guarani.hexagonal.guarani.persona.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.persona.domain.model.PersonaGuarani;
import java.util.Optional;

public interface PersonaGuaraniRepository {
    Optional<PersonaGuarani> findByPersonaId(Integer personaId);
}
