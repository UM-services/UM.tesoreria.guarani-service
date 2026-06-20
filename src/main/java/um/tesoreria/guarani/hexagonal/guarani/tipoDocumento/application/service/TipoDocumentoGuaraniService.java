package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.ports.in.GetAllTipoDocumentosUseCase;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.ports.in.GetTipoDocumentoGuaraniUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDocumentoGuaraniService {

    private final GetTipoDocumentoGuaraniUseCase getTipoDocumentoGuaraniUseCase;
    private final GetAllTipoDocumentosUseCase getAllTipoDocumentosUseCase;

    public TipoDocumentoGuarani getByTipoDocumento(Short tipoDocumento) {
        return getTipoDocumentoGuaraniUseCase.getByTipoDocumento(tipoDocumento);
    }

    public List<TipoDocumentoGuarani> getAllTipoDocumentos() {
        return getAllTipoDocumentosUseCase.getAll();
    }
}
