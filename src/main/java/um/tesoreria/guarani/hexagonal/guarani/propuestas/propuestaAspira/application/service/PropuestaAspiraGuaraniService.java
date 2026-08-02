package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in.GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in.GetPropuestaAspiraGuaraniUseCase;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropuestaAspiraGuaraniService {

    private final GetPropuestaAspiraGuaraniUseCase getPropuestaAspiraGuaraniUseCase;
    private final GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase getPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase;

    public PropuestaAspiraGuarani getByPropuestaAspiraId(Integer propuestaAspiraId) {
        return getPropuestaAspiraGuaraniUseCase.getByPropuestaAspiraId(propuestaAspiraId);
    }

    public List<PropuestaAspiraGuarani> getByPropuestaUbicacionAndFechaInscripcion(
            Integer propuesta, Integer ubicacion, LocalDate fechaDesde) {
        return getPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase
                .getByPropuestaUbicacionAndFechaInscripcion(propuesta, ubicacion, fechaDesde);
    }
}
