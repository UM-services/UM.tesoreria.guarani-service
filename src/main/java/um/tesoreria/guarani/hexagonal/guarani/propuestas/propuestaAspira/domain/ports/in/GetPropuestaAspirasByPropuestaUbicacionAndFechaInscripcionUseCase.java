package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.domain.model.PropuestaAspiraGuarani;

import java.time.LocalDate;
import java.util.List;

public interface GetPropuestaAspirasByPropuestaUbicacionAndFechaInscripcionUseCase {
    List<PropuestaAspiraGuarani> getByPropuestaUbicacionAndFechaInscripcion(
            Integer propuesta, Integer ubicacion, LocalDate fechaDesde);
}
