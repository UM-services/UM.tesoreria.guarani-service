package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.entity.PersonaDocumentoGuaraniEntity;

@Repository
public interface JpaPersonaDocumentoGuaraniRepository extends JpaRepository<PersonaDocumentoGuaraniEntity, Integer> {
}
