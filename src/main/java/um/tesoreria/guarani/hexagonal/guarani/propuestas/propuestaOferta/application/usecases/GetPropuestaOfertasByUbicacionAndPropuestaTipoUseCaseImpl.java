package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.application.usecases;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model.PropuestaOfertaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.in.GetPropuestaOfertasByUbicacionAndPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.out.PropuestaOfertaGuaraniRepository;

@Component
@RequiredArgsConstructor
public class GetPropuestaOfertasByUbicacionAndPropuestaTipoUseCaseImpl
        implements GetPropuestaOfertasByUbicacionAndPropuestaTipoUseCase {
    private final PropuestaOfertaGuaraniRepository repository;

    @Override
    public List<PropuestaOfertaGuarani> getByUbicacionAndPropuestaTipo(
            Integer ubicacion, Integer propuestaTipo) {
        return repository.findAllByUbicacionAndPropuestaTipo(ubicacion, propuestaTipo);
    }
}
