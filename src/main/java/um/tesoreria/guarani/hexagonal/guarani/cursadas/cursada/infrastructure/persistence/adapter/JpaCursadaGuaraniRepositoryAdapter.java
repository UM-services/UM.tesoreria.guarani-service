package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.ports.out.CursadaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.ElementoPlanGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.InscripcionCursadaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.mapper.CursadaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.repository.JpaElementoPlanGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.repository.JpaInscripcionCursadaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.repository.JpaPropuestaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.entity.PropuestaResponsableAcademicaGuaraniEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaCursadaGuaraniRepositoryAdapter implements CursadaGuaraniRepository {

    // Estado 'A' (Aceptada) de sga_inscripciones_estados. Las bajas se borran de la tabla.
    static final String ESTADO_ACEPTADA = "A";

    private final JpaInscripcionCursadaGuaraniRepository jpaInscripcionRepository;
    private final JpaElementoPlanGuaraniRepository jpaElementoPlanRepository;
    private final JpaPropuestaGuaraniRepository jpaPropuestaRepository;
    private final CursadaGuaraniMapper mapper;

    private record ParPlan(Integer planVersion, Integer elemento) {
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursadaGuarani> findAllByAlumnoInAndAnioAcademico(Collection<Integer> alumnos, Integer anioAcademico) {
        var inscripciones = jpaInscripcionRepository
                .findAllByAlumnoInAndEstadoAndComisionRel_PeriodoLectivoRel_PeriodoRel_AnioAcademico(
                        alumnos, ESTADO_ACEPTADA, BigDecimal.valueOf(anioAcademico));
        if (inscripciones.isEmpty()) {
            return List.of();
        }
        Map<ParPlan, List<ElementoPlanGuaraniEntity>> planes = planesPorPar(inscripciones);
        Map<Integer, PropuestaGuaraniEntity> propuestas = propuestasPorId(inscripciones);
        Map<Integer, Integer> responsables = new HashMap<>();
        return inscripciones.stream()
                .map(inscripcion -> {
                    Integer propuestaId = inscripcion.getAlumnoRel().getPropuesta();
                    var par = new ParPlan(inscripcion.getPlanVersion(), inscripcion.getComisionRel().getElemento());
                    return mapper.toDomain(
                            inscripcion,
                            planes.getOrDefault(par, List.of()),
                            propuestas.get(propuestaId),
                            responsables.computeIfAbsent(propuestaId, id -> responsableDe(propuestas.get(id))));
                })
                .toList();
    }

    // IN independientes traen combinaciones de más: se reagrupa por el par exacto.
    private Map<ParPlan, List<ElementoPlanGuaraniEntity>> planesPorPar(List<InscripcionCursadaGuaraniEntity> inscripciones) {
        Set<Integer> planVersiones = inscripciones.stream()
                .map(InscripcionCursadaGuaraniEntity::getPlanVersion)
                .collect(Collectors.toSet());
        Set<Integer> elementos = inscripciones.stream()
                .map(inscripcion -> inscripcion.getComisionRel().getElemento())
                .collect(Collectors.toSet());
        return jpaElementoPlanRepository
                .findAllByPlanVersionInAndElementoRevisionRel_ElementoIn(planVersiones, elementos).stream()
                .collect(Collectors.groupingBy(plan ->
                        new ParPlan(plan.getPlanVersion(), plan.getElementoRevisionRel().getElemento())));
    }

    private Map<Integer, PropuestaGuaraniEntity> propuestasPorId(List<InscripcionCursadaGuaraniEntity> inscripciones) {
        Set<Integer> ids = inscripciones.stream()
                .map(inscripcion -> inscripcion.getAlumnoRel().getPropuesta())
                .collect(Collectors.toSet());
        return jpaPropuestaRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(PropuestaGuaraniEntity::getPropuesta, propuesta -> propuesta));
    }

    // Sin responsables: null. Con varias: la de menor id, y se avisa (sin datos personales).
    private Integer responsableDe(PropuestaGuaraniEntity propuesta) {
        if (propuesta == null || propuesta.getResponsablesAcademicas() == null) {
            return null;
        }
        var ids = propuesta.getResponsablesAcademicas().stream()
                .map(PropuestaResponsableAcademicaGuaraniEntity::getResponsableAcademica)
                .sorted(Comparator.naturalOrder())
                .toList();
        if (ids.size() > 1) {
            log.warn("Propuesta {} con {} responsables académicas: se usa la de menor id", propuesta.getPropuesta(), ids.size());
        }
        return ids.isEmpty() ? null : ids.get(0);
    }
}
