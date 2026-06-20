package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.application.exception.PersonaDocumentoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.model.PersonaDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.ports.in.GetPersonaDocumentoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.ports.out.PersonaDocumentoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPersonaDocumentoGuaraniUseCaseImpl implements GetPersonaDocumentoGuaraniUseCase {

    private final PersonaDocumentoGuaraniRepository repository;

    @Override
    public PersonaDocumentoGuarani getByDocumento(Integer documento) {
        return repository.findByDocumento(documento).orElseThrow(() -> new PersonaDocumentoGuaraniException(documento));
    }

}
