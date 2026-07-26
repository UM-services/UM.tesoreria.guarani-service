package um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;
import java.util.Optional;

public interface PersonaGuaraniRepository {
    Optional<PersonaGuarani> findByPersonaId(Integer personaId);
}
