package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;

import java.util.List;
import java.util.Optional;

public interface TipoDocumentoGuaraniRepository {
    Optional<TipoDocumentoGuarani> findByTipoDocumento(Short tipoDocumento);
    List<TipoDocumentoGuarani> findAll();
}
