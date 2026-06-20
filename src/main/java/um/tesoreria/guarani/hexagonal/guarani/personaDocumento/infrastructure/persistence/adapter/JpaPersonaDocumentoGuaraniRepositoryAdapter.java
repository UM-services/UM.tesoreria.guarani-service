package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.model.PersonaDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.ports.out.PersonaDocumentoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.mapper.PersonaDocumentoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.repository.JpaPersonaDocumentoGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaPersonaDocumentoGuaraniRepositoryAdapter implements PersonaDocumentoGuaraniRepository {

    private final JpaPersonaDocumentoGuaraniRepository jpaPersonaDocumentoGuaraniRepository;
    private final PersonaDocumentoGuaraniMapper mapper;

    @Override
    public Optional<PersonaDocumentoGuarani> findByDocumento(Integer documento) {
        return jpaPersonaDocumentoGuaraniRepository.findById(documento)
                .map(mapper::toDomain);
    }

}
