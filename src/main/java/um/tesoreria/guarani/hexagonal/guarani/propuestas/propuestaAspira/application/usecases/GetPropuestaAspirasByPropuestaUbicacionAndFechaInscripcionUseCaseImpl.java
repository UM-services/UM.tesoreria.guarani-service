package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in.GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.out.PropuestaAspiraGuaraniRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCaseImpl
        implements GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase {

    private final PropuestaAspiraGuaraniRepository repository;

    @Override
    public List<PropuestaAspiraGuarani> getByPropuestaUbicacionAndFechaInscripcion(
            Integer propuesta, Integer ubicacion, LocalDate fechaDesde) {
        return repository.findAllByPropuestaAndUbicacionAndFechaInscripcionFrom(propuesta, ubicacion, fechaDesde);
    }
}
