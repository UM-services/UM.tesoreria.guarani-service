package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.model.PersonaDocumentoGuarani;

import java.util.List;

public interface GetPersonaDocumentoGuaraniUseCase {
    PersonaDocumentoGuarani getByDocumento(Integer documento);
}
