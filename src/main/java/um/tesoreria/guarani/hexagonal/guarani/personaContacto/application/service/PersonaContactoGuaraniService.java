package um.tesoreria.guarani.hexagonal.guarani.personaContacto.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.model.PersonaContactoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.ports.in.GetPersonaContactoGuaraniUseCase;

@Service
@RequiredArgsConstructor
public class PersonaContactoGuaraniService {

    private final GetPersonaContactoGuaraniUseCase getPersonaContactoGuaraniUseCase;

    public PersonaContactoGuarani getByPersonaContacto(Integer personaContacto) {
        return getPersonaContactoGuaraniUseCase.getByPersonaContacto(personaContacto);
    }

}
