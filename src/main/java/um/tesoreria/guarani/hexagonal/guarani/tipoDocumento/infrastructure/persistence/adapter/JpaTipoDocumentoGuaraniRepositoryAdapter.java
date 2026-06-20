package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.ports.out.TipoDocumentoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.mapper.TipoDocumentoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.repository.JpaTipoDocumentoGuaraniRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaTipoDocumentoGuaraniRepositoryAdapter implements TipoDocumentoGuaraniRepository {

    private final JpaTipoDocumentoGuaraniRepository jpaTipoDocumentoGuaraniRepository;
    private final TipoDocumentoGuaraniMapper mapper;

    @Override
    public Optional<TipoDocumentoGuarani> findByTipoDocumento(Short tipoDocumento) {
        return jpaTipoDocumentoGuaraniRepository.findById(tipoDocumento)
                .map(mapper::toDomain);
    }

    @Override
    public List<TipoDocumentoGuarani> findAll() {
        return jpaTipoDocumentoGuaraniRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
