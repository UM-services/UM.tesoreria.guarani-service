package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.model.RequisitoTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.in.GetAllRequisitoTiposUseCase;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.domain.ports.in.GetRequisitoTipoGuaraniUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisitoTipoGuaraniService {

    private final GetRequisitoTipoGuaraniUseCase getRequisitoTipoGuaraniUseCase;
    private final GetAllRequisitoTiposUseCase getAllRequisitoTiposUseCase;

    public RequisitoTipoGuarani getByRequisitoTipo(Integer requisitoTipo) {
        return getRequisitoTipoGuaraniUseCase.getByRequisitoTipo(requisitoTipo);
    }

    public List<RequisitoTipoGuarani> getAllRequisitoTipos() {
        return getAllRequisitoTiposUseCase.getAll();
    }
}
