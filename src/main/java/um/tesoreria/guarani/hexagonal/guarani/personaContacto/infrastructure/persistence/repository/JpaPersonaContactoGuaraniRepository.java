package um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.entity.PersonaContactoGuaraniEntity;

@Repository
public interface JpaPersonaContactoGuaraniRepository extends JpaRepository<PersonaContactoGuaraniEntity, Integer> {
}
