package um.tesoreria.guarani.hexagonal.guarani.persona.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.persona.application.exception.PersonaGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.persona.domain.ports.in.GetPersonaGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.persona.domain.ports.out.PersonaGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetPersonaGuaraniUseCaseImpl implements GetPersonaGuaraniUseCase {

    private final PersonaGuaraniRepository repository;

    @Override
    public PersonaGuarani getByPersonaId(Integer personaId) {
        return repository.findByPersonaId(personaId).orElseThrow(() -> new PersonaGuaraniException(personaId));
    }
}
