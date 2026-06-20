package um.tesoreria.guarani.hexagonal.guarani.propuesta.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.model.PropuestaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.ports.in.GetAllPropuestasUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.domain.ports.in.GetPropuestaGuaraniUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropuestaGuaraniService {

    private final GetPropuestaGuaraniUseCase getPropuestaGuaraniUseCase;
    private final GetAllPropuestasUseCase getAllPropuestasUseCase;

    public PropuestaGuarani getByPropuestaId(Integer propuestaId) {
        return getPropuestaGuaraniUseCase.getByPropuestaId(propuestaId);
    }

    public List<PropuestaGuarani> getAllPropuestas() {
        return getAllPropuestasUseCase.getAll();
    }
}
