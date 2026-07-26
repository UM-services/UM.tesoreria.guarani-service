package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.application.exception.PersonaDocumentoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.model.PersonaDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.ports.in.GetPersonaDocumentoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.ports.out.PersonaDocumentoGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetPersonaDocumentoGuaraniUseCaseImpl implements GetPersonaDocumentoGuaraniUseCase {

    private final PersonaDocumentoGuaraniRepository repository;

    @Override
    public PersonaDocumentoGuarani getByDocumento(Integer documento) {
        return repository.findByDocumento(documento).orElseThrow(() -> new PersonaDocumentoGuaraniException(documento));
    }

}
