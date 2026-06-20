package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.in.GetAllPropuestaTiposUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.ports.in.GetPropuestaTipoGuaraniUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropuestaTipoGuaraniService {

    private final GetPropuestaTipoGuaraniUseCase getPropuestaTipoGuaraniUseCase;
    private final GetAllPropuestaTiposUseCase getAllPropuestaTiposUseCase;

    public PropuestaTipoGuarani getByPropuestaTipo(Integer propuestaTipo) {
        return getPropuestaTipoGuaraniUseCase.getByPropuestaTipo(propuestaTipo);
    }

    public List<PropuestaTipoGuarani> getAllPropuestaTipos() {
        return getAllPropuestaTiposUseCase.getAll();
    }
}
