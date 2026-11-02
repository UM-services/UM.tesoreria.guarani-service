package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.mapper.CursadaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.repository.JpaElementoPlanGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.repository.JpaInscripcionCursadaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.repository.JpaPropuestaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.entity.PropuestaResponsableAcademicaGuaraniEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.CursadaEntityFixtures.elementoPlan;
import static um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.CursadaEntityFixtures.inscripcion;

@ExtendWith(MockitoExtension.class)
class JpaCursadaGuaraniRepositoryAdapterTest {

    @Mock
    private JpaInscripcionCursadaGuaraniRepository jpaInscripcionRepository;

    @Mock
    private JpaElementoPlanGuaraniRepository jpaElementoPlanRepository;

    @Mock
    private JpaPropuestaGuaraniRepository jpaPropuestaRepository;

    @Spy
    private CursadaGuaraniMapper mapper = new CursadaGuaraniMapper();

    @InjectMocks
    private JpaCursadaGuaraniRepositoryAdapter adapter;

    /*
     * protects: la consulta filtra por estado "A" y compara el año con un BigDecimal (anio_academico es numeric).
     * fails_when: se consulta con otro estado, con un Integer, o se quita el filtro de estado y entran inscripciones no vigentes.
     * no_claim: no prueba que Postgres aplique el filtro (lo cubre el IT) ni el borrado de bajas.
     */
    @Test
    void queriesAcceptedInscripcionesWithABigDecimalYear() {
        when(jpaInscripcionRepository.findAllByAlumnoInAndEstadoAndComisionRel_PeriodoLectivoRel_PeriodoRel_AnioAcademico(
                any(), any(), any())).thenReturn(List.of());

        adapter.findAllByAlumnoInAndAnioAcademico(Set.of(1, 2), 2026);

        verify(jpaInscripcionRepository)
                .findAllByAlumnoInAndEstadoAndComisionRel_PeriodoLectivoRel_PeriodoRel_AnioAcademico(
                        Set.of(1, 2), "A", BigDecimal.valueOf(2026));
    }

    /*
     * protects: sin inscripciones no se consultan planes ni propuestas.
     * fails_when: se hacen consultas de más con conjuntos vacíos.
     * no_claim: no prueba el rendimiento de las consultas.
     */
    @Test
    void doesNotQueryPlansWhenThereAreNoInscripciones() {
        when(jpaInscripcionRepository.findAllByAlumnoInAndEstadoAndComisionRel_PeriodoLectivoRel_PeriodoRel_AnioAcademico(
                any(), any(), any())).thenReturn(List.of());

        assertThat(adapter.findAllByAlumnoInAndAnioAcademico(Set.of(1), 2026)).isEmpty();

        verifyNoInteractions(jpaElementoPlanRepository, jpaPropuestaRepository);
    }

    /*
     * protects: anioPlan se toma del par exacto (planVersion, elemento); el IN independiente trae combinaciones de más.
     * fails_when: una fila del plan 8 o del elemento 41 cambia el anioPlan de la inscripción del plan 7 y elemento 40.
     * no_claim: no prueba la consulta de planes contra Postgres.
     */
    @Test
    void groupsPlansByTheExactPlanVersionAndElementPair() {
        var insc = inscripcion(11, 7, 40, "ACT-1", 4321, 1, "Anual", 345, (short) 1);
        when(jpaInscripcionRepository.findAllByAlumnoInAndEstadoAndComisionRel_PeriodoLectivoRel_PeriodoRel_AnioAcademico(
                any(), any(), any())).thenReturn(List.of(insc));
        when(jpaElementoPlanRepository.findAllByPlanVersionInAndElementoRevisionRel_ElementoIn(anyCollection(), anyCollection()))
                .thenReturn(List.of(
                        elementoPlan(1, 7, 40, (short) 2),
                        elementoPlan(2, 8, 40, (short) 4),
                        elementoPlan(3, 7, 41, (short) 5)));
        when(jpaPropuestaRepository.findAllById(any())).thenReturn(List.of());

        var resultado = adapter.findAllByAlumnoInAndAnioAcademico(Set.of(111), 2026);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getAnioPlan()).isEqualTo(2);
        verify(jpaElementoPlanRepository).findAllByPlanVersionInAndElementoRevisionRel_ElementoIn(Set.of(7), Set.of(40));
    }

    /*
     * protects: sin filas en el plan del par, anioPlan queda null y la cursada igual se devuelve.
     * fails_when: la cursada se descarta o lanza excepción cuando no hay plan.
     * no_claim: no prueba qué pasa con cursadas sin plan en Postgres.
     */
    @Test
    void keepsTheCursadaWithANullAnioPlanWhenThePairHasNoPlan() {
        var insc = inscripcion(11, 7, 40, "ACT-1", 4321, 1, "Anual", 345, (short) 1);
        when(jpaInscripcionRepository.findAllByAlumnoInAndEstadoAndComisionRel_PeriodoLectivoRel_PeriodoRel_AnioAcademico(
                any(), any(), any())).thenReturn(List.of(insc));
        when(jpaElementoPlanRepository.findAllByPlanVersionInAndElementoRevisionRel_ElementoIn(anyCollection(), anyCollection()))
                .thenReturn(List.of(elementoPlan(2, 8, 40, (short) 4)));
        when(jpaPropuestaRepository.findAllById(any())).thenReturn(List.of());

        var resultado = adapter.findAllByAlumnoInAndAnioAcademico(Set.of(111), 2026);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getAnioPlan()).isNull();
    }

    /*
     * protects: la responsable académica es la de menor id; sin responsables es null; el nombre sale de la propuesta.
     * fails_when: se toma la primera de la colección sin ordenar, o una propuesta sin responsables lanza excepción.
     * no_claim: no prueba el WARN (sin datos personales) ni la carga lazy de la colección en Hibernate.
     */
    @Test
    void picksTheLowestResponsableOrNull() {
        var conVarias = inscripcion(11, 7, 40, "ACT-1", 4321, 1, "Anual", 345, (short) 1);
        var sinResponsables = inscripcion(12, 7, 41, "ACT-2", 4321, 1, "Anual", 346, (short) 1);
        when(jpaInscripcionRepository.findAllByAlumnoInAndEstadoAndComisionRel_PeriodoLectivoRel_PeriodoRel_AnioAcademico(
                any(), any(), any())).thenReturn(List.of(conVarias, sinResponsables));
        when(jpaElementoPlanRepository.findAllByPlanVersionInAndElementoRevisionRel_ElementoIn(anyCollection(), anyCollection()))
                .thenReturn(List.of());
        when(jpaPropuestaRepository.findAllById(any())).thenReturn(List.of(
                propuesta(345, "Propuesta A", 30, 12, 20),
                propuesta(346, "Propuesta B")));

        var resultado = adapter.findAllByAlumnoInAndAnioAcademico(Set.of(111, 112), 2026);

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getResponsableAcademica()).isEqualTo(12);
        assertThat(resultado.get(0).getPropuestaNombre()).isEqualTo("Propuesta A");
        assertThat(resultado.get(1).getResponsableAcademica()).isNull();
        verify(jpaPropuestaRepository).findAllById(eq(Set.of(345, 346)));
    }

    private static PropuestaGuaraniEntity propuesta(int id, String nombre, Integer... responsables) {
        var ras = new ArrayList<PropuestaResponsableAcademicaGuaraniEntity>();
        for (Integer responsable : responsables) {
            ras.add(PropuestaResponsableAcademicaGuaraniEntity.builder().propuesta(id).responsableAcademica(responsable).build());
        }
        return PropuestaGuaraniEntity.builder().propuesta(id).nombre(nombre).responsablesAcademicas(ras).build();
    }
}
