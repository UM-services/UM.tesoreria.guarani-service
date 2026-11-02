package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.ports.in.GetCursadasByNroDocumentoAndAnioAcademicoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.ports.out.CursadaGuaraniRepository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetCursadasByNroDocumentoAndAnioAcademicoUseCaseImpl implements GetCursadasByNroDocumentoAndAnioAcademicoUseCase {

    private static final Comparator<CursadaGuarani> ORDEN = Comparator
            .comparing(CursadaGuarani::getPropuesta, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(CursadaGuarani::getPlanVersion, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(CursadaGuarani::getActividadCodigo, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(CursadaGuarani::getPeriodoLectivo, Comparator.nullsLast(Comparator.naturalOrder()));

    private final GetAlumnosByNroDocumentoUseCase getAlumnosByNroDocumentoUseCase;
    private final CursadaGuaraniRepository repository;

    private record Clave(Integer propuesta, Integer planVersion, String actividadCodigo, Integer periodoLectivo) {
    }

    @Override
    public List<CursadaGuarani> getByNroDocumentoAndAnioAcademico(String nroDocumento, Integer anioAcademico) {
        // El use case de alumno también devuelve personas sin alumno (alumno == null): se descartan.
        Set<Integer> alumnos = getAlumnosByNroDocumentoUseCase.getByNroDocumento(nroDocumento).stream()
                .map(AlumnoGuarani::getAlumno)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (alumnos.isEmpty()) {
            return List.of();
        }
        return sinClavesRepetidas(repository.findAllByAlumnoInAndAnioAcademico(alumnos, anioAcademico)).stream()
                .sorted(ORDEN)
                .toList();
    }

    // Una fila por clave: se conserva la inscripción de menor id.
    private List<CursadaGuarani> sinClavesRepetidas(List<CursadaGuarani> cursadas) {
        var porClave = new LinkedHashMap<Clave, CursadaGuarani>();
        int repetidas = 0;
        for (CursadaGuarani cursada : cursadas) {
            var clave = new Clave(cursada.getPropuesta(), cursada.getPlanVersion(),
                    cursada.getActividadCodigo(), cursada.getPeriodoLectivo());
            var actual = porClave.get(clave);
            if (actual == null) {
                porClave.put(clave, cursada);
                continue;
            }
            repetidas++;
            if (cursada.getInscripcion() < actual.getInscripcion()) {
                porClave.put(clave, cursada);
            }
        }
        if (repetidas > 0) {
            log.warn("Cursadas con clave repetida: {} filas descartadas (se conserva la inscripción de menor id)", repetidas);
        }
        return List.copyOf(porClave.values());
    }
}
