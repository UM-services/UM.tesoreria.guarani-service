package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.model.PersonaDocumentoGuarani;

public interface GetPersonaDocumentoGuaraniUseCase {
    PersonaDocumentoGuarani getByDocumento(Integer documento);
}
