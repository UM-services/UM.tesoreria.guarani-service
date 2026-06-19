package um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.persona.domain.ports.out.PersonaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.persistence.mapper.PersonaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.persistence.repository.JpaPersonaGuaraniRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaPersonaGuaraniRepositoryAdapter implements PersonaGuaraniRepository {

    private final JpaPersonaGuaraniRepository jpaPersonaGuaraniRepository;
    private final PersonaGuaraniMapper mapper;

    @Override
    public Optional<PersonaGuarani> findByPersonaId(Integer personaId) {
        return jpaPersonaGuaraniRepository.findById(personaId)
                .map(mapper::toDomain);
    }
}
