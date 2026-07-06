package um.tesoreria.guarani.hexagonal.guarani.propuestaAspira.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestaAspira.domain.ports.in.GetAllPropuestaAspirasUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestaAspira.domain.ports.in.GetPropuestaAspiraGuaraniUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropuestaAspiraGuaraniService {

    private final GetPropuestaAspiraGuaraniUseCase getPropuestaAspiraGuaraniUseCase;
    private final GetAllPropuestaAspirasUseCase getAllPropuestaAspirasUseCase;

    public PropuestaAspiraGuarani getByPropuestaAspiraId(Integer propuestaAspiraId) {
        return getPropuestaAspiraGuaraniUseCase.getByPropuestaAspiraId(propuestaAspiraId);
    }

    public List<PropuestaAspiraGuarani> getAllPropuestaAspiras() {
        return getAllPropuestaAspirasUseCase.getAll();
    }
}
