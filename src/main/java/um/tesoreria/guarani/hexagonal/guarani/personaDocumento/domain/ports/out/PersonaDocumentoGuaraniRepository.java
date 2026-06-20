package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.model.PersonaDocumentoGuarani;

import java.util.Optional;

public interface PersonaDocumentoGuaraniRepository {
    Optional<PersonaDocumentoGuarani> findByDocumento(Integer documento);
}
