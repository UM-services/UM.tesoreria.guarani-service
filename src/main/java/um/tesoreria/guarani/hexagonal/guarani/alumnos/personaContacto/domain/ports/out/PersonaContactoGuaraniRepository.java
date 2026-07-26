package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.domain.model.PersonaContactoGuarani;

import java.util.List;
import java.util.Optional;

public interface PersonaContactoGuaraniRepository {
    Optional<PersonaContactoGuarani> findByPersonaContacto(Integer personaContacto);
    List<PersonaContactoGuarani> findAll();
}
