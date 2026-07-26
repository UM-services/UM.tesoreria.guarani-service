package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.application.exception.PersonaContactoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.domain.model.PersonaContactoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.domain.ports.in.GetPersonaContactoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.domain.ports.out.PersonaContactoGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetPersonaContactoGuaraniUseCaseImpl implements GetPersonaContactoGuaraniUseCase {

    private final PersonaContactoGuaraniRepository repository;

    @Override
    public PersonaContactoGuarani getByPersonaContacto(Integer personaContacto) {
        return repository.findByPersonaContacto(personaContacto).orElseThrow(() -> new PersonaContactoGuaraniException(personaContacto));
    }

}
