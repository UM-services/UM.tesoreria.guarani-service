package um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.domain.model.TipoDocumentoGuarani;

import java.util.List;

public interface GetAllTipoDocumentosUseCase {
    List<TipoDocumentoGuarani> getAll();
}
