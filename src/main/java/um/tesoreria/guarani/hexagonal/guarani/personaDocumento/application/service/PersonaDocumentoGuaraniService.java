package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.model.PersonaDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.ports.in.GetPersonaDocumentoGuaraniUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaDocumentoGuaraniService {

    private final GetPersonaDocumentoGuaraniUseCase getPersonaDocumentoGuaraniUseCase;

    public PersonaDocumentoGuarani getByDocumento(Integer documento) {
        return getPersonaDocumentoGuaraniUseCase.getByDocumento(documento);
    }

}
