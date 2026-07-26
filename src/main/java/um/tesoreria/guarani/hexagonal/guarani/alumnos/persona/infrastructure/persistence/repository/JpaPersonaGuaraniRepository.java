package um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.entity.PersonaGuaraniEntity;

@Repository
public interface JpaPersonaGuaraniRepository extends JpaRepository<PersonaGuaraniEntity, Integer> {
}
