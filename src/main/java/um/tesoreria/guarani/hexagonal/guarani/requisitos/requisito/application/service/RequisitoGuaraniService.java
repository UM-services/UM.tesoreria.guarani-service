package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.model.RequisitoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.in.GetAllRequisitosUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.in.GetRequisitoGuaraniUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.domain.ports.in.GetRequisitosByTipoUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoGuaraniService {

    private final GetRequisitoGuaraniUseCase getRequisitoGuaraniUseCase;
    private final GetAllRequisitosUseCase getAllRequisitosUseCase;
    private final GetRequisitosByTipoUseCase getRequisitosByTipoUseCase;

    public RequisitoGuarani getByRequisitoId(Integer requisitoId) {
        return getRequisitoGuaraniUseCase.getByRequisitoId(requisitoId);
    }

    public List<RequisitoGuarani> getAllRequisitos() {
        return getAllRequisitosUseCase.getAll();
    }

    public List<RequisitoGuarani> getByRequisitoTipo(Integer requisitoTipo) {
        return getRequisitosByTipoUseCase.getByRequisitoTipo(requisitoTipo);
    }
}
