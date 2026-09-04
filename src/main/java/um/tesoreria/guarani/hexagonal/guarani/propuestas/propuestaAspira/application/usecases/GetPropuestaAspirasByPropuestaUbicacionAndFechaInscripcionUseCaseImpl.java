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
    public List<PropuestaAspiraGuarani> getByPropuestaUbicacionFechaInscripcionAndAnioAcademico(
            Integer propuesta, Integer ubicacion, LocalDate fechaDesde, Integer anioAcademico) {
        return repository.findAllByPropuestaAndUbicacionAndFechaInscripcionFromAndAnioAcademico(
                propuesta, ubicacion, fechaDesde, anioAcademico);
    }
}
