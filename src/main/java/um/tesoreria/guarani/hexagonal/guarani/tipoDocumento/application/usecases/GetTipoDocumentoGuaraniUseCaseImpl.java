package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.application.exception.TipoDocumentoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.ports.in.GetTipoDocumentoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.ports.out.TipoDocumentoGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetTipoDocumentoGuaraniUseCaseImpl implements GetTipoDocumentoGuaraniUseCase {

    private final TipoDocumentoGuaraniRepository repository;

    @Override
    public TipoDocumentoGuarani getByTipoDocumento(Short tipoDocumento) {
        return repository.findByTipoDocumento(tipoDocumento).orElseThrow(() -> new TipoDocumentoGuaraniException(tipoDocumento));
    }

}
