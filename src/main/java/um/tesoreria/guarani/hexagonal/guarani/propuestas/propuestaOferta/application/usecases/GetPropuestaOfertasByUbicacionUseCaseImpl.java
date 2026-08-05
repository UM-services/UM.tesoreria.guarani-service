package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.application.usecases;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model.PropuestaOfertaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.in.GetPropuestaOfertasByUbicacionUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.out.PropuestaOfertaGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetPropuestaOfertasByUbicacionUseCaseImpl implements GetPropuestaOfertasByUbicacionUseCase {
    private final PropuestaOfertaGuaraniRepository repository;

    @Override
    public List<PropuestaOfertaGuarani> getByUbicacion(Integer ubicacion) {
        return repository.findAllByUbicacion(ubicacion);
    }
}
