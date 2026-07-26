package um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.ports.in.GetPersonaGuaraniUseCase;

@Service
@RequiredArgsConstructor
public class PersonaGuaraniService {

    private final GetPersonaGuaraniUseCase getPersonaGuaraniUseCase;

    public PersonaGuarani getByPersonaId(Integer personaId) {
        return getPersonaGuaraniUseCase.getByPersonaId(personaId);
    }
}
