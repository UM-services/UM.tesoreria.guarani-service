package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;

import java.util.List;

public interface GetAllTipoDocumentosUseCase {
    List<TipoDocumentoGuarani> getAll();
}
