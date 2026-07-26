package um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.domain.model.TipoDocumentoGuarani;

public interface GetTipoDocumentoGuaraniUseCase {
    TipoDocumentoGuarani getByTipoDocumento(Short tipoDocumento);
}
