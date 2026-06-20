package um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.model.PersonaContactoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.domain.ports.out.PersonaContactoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.mapper.PersonaContactoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.repository.JpaPersonaContactoGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaPersonaContactoGuaraniRepositoryAdapter implements PersonaContactoGuaraniRepository {

    private final JpaPersonaContactoGuaraniRepository jpaPersonaContactoGuaraniRepository;
    private final PersonaContactoGuaraniMapper mapper;

    @Override
    public Optional<PersonaContactoGuarani> findByPersonaContacto(Integer personaContacto) {
        return jpaPersonaContactoGuaraniRepository.findById(personaContacto)
                .map(mapper::toDomain);
    }

    @Override
    public List<PersonaContactoGuarani> findAll() {
        return jpaPersonaContactoGuaraniRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
