package um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.domain.model.TipoDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.domain.ports.in.GetAllTipoDocumentosUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.domain.ports.out.TipoDocumentoGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllTipoDocumentosUseCaseImpl implements GetAllTipoDocumentosUseCase {

    private final TipoDocumentoGuaraniRepository repository;

    @Override
    public List<TipoDocumentoGuarani> getAll() {
        return repository.findAll();
    }

}
