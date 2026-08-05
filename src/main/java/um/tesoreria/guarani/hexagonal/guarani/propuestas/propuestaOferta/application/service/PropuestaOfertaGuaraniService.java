package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model.PropuestaOfertaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.in.GetPropuestaOfertasByUbicacionUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.in.GetPropuestaOfertasByUbicacionAndPropuestaTipoUseCase;

@Service
@RequiredArgsConstructor
public class PropuestaOfertaGuaraniService {
    private final GetPropuestaOfertasByUbicacionUseCase getByUbicacionUseCase;
    private final GetPropuestaOfertasByUbicacionAndPropuestaTipoUseCase getByUbicacionAndPropuestaTipoUseCase;

    public List<PropuestaOfertaGuarani> getByUbicacion(Integer ubicacion) {
        return getByUbicacionUseCase.getByUbicacion(ubicacion);
    }

    public List<PropuestaOfertaGuarani> getByUbicacionPropuestaTipo204(Integer ubicacion) {
        return getByUbicacionAndPropuestaTipoUseCase.getByUbicacionAndPropuestaTipo(ubicacion, 204);
    }
}
