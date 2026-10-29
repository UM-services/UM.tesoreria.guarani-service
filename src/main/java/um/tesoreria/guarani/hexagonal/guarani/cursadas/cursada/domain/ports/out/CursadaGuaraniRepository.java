package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;

import java.util.Collection;
import java.util.List;

public interface CursadaGuaraniRepository {
    List<CursadaGuarani> findAllByAlumnoInAndAnioAcademico(Collection<Integer> alumnos, Integer anioAcademico);
}
