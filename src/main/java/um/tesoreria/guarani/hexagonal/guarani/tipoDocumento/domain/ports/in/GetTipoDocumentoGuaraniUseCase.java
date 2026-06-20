package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;

public interface GetTipoDocumentoGuaraniUseCase {
    TipoDocumentoGuarani getByTipoDocumento(Short tipoDocumento);
}
