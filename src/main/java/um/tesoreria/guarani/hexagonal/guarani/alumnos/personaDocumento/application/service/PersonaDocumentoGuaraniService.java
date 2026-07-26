package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.model.PersonaDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.ports.in.GetPersonaDocumentoGuaraniUseCase;

@Service
@RequiredArgsConstructor
public class PersonaDocumentoGuaraniService {

    private final GetPersonaDocumentoGuaraniUseCase getPersonaDocumentoGuaraniUseCase;

    public PersonaDocumentoGuarani getByDocumento(Integer documento) {
        return getPersonaDocumentoGuaraniUseCase.getByDocumento(documento);
    }

}
