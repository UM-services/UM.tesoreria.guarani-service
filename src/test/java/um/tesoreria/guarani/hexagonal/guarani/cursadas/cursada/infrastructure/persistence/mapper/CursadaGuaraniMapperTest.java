package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.PeriodoCursada;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.CursadaEntityFixtures.elementoPlan;
import static um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.CursadaEntityFixtures.inscripcion;

class CursadaGuaraniMapperTest {

    private final CursadaGuaraniMapper mapper = new CursadaGuaraniMapper();

    private static final Short ANIO_CURSADA = 2;

    /*
     * protects: cada campo del contrato sale de la columna correcta, y el período sale del id genérico.
     * fails_when: se cruzan campos (por ejemplo nombre de actividad y de propuesta), el período no se traduce
     *             o periodoNombre no es el nombre del período genérico.
     * no_claim: no prueba que las columnas existan en Postgres (eso lo cubre el IT).
     */
    @Test
    void mapsEveryContractField() {
        var inscripcion = inscripcion(11, 7, 40, "ACT-1", 4321, 3, "2do Cuatrimestre", 345, ANIO_CURSADA);
        var propuesta = PropuestaGuaraniEntity.builder().propuesta(345).nombre("Propuesta sintética").build();

        var cursada = mapper.toDomain(inscripcion, List.of(elementoPlan(1, 7, 40, (short) 1)), propuesta, 12, Map.of());

        assertThat(cursada.getInscripcion()).isEqualTo(11);
        assertThat(cursada.getResponsableAcademica()).isEqualTo(12);
        assertThat(cursada.getPropuesta()).isEqualTo(345);
        assertThat(cursada.getPropuestaNombre()).isEqualTo("Propuesta sintética");
        assertThat(cursada.getPlanVersion()).isEqualTo(7);
        assertThat(cursada.getActividadCodigo()).isEqualTo("ACT-1");
        assertThat(cursada.getActividadNombre()).isEqualTo("Actividad ACT-1");
        assertThat(cursada.getAnioPlan()).isEqualTo(1);
        assertThat(cursada.getPeriodo()).isEqualTo(PeriodoCursada.SEGUNDO_SEMESTRE);
        assertThat(cursada.getPeriodoLectivo()).isEqualTo(4321);
        assertThat(cursada.getPeriodoNombre()).isEqualTo("2do Cuatrimestre");
        assertThat(cursada.getAnioCursada()).isEqualTo(ANIO_CURSADA);
    }

    /*
     * protects: anioPlan es null cuando no se puede determinar (decisión de #63).
     * fails_when: con cero filas lanza excepción o devuelve 0; con dos años distintos elige uno;
     *             con un año y un null mezclados devuelve el año.
     * no_claim: no define cuál de las revisiones es la correcta cuando difieren.
     */
    @Test
    void anioPlanIsNullWhenItCannotBeDetermined() {
        var inscripcion = inscripcion(11, 7, 40, "ACT-1", 4321, 1, "Anual", 345, ANIO_CURSADA);

        assertThat(mapper.toDomain(inscripcion, List.of(), null, null, Map.of()).getAnioPlan()).isNull();
        assertThat(mapper.toDomain(inscripcion,
                List.of(elementoPlan(1, 7, 40, (short) 1), elementoPlan(2, 7, 40, (short) 2)), null, null, Map.of()).getAnioPlan()).isNull();
        assertThat(mapper.toDomain(inscripcion,
                List.of(elementoPlan(1, 7, 40, (short) 1), elementoPlan(2, 7, 40, null)), null, null, Map.of()).getAnioPlan()).isNull();
    }

    /*
     * protects: varias revisiones con el mismo año del plan dan ese año.
     * fails_when: las filas repetidas con el mismo valor se tratan como ambiguas y devuelven null.
     * no_claim: no prueba el agrupado por par (planVersion, elemento); eso es del adaptador.
     */
    @Test
    void anioPlanIsTheValueWhenAllRevisionsAgree() {
        var inscripcion = inscripcion(11, 7, 40, "ACT-1", 4321, 1, "Anual", 345, ANIO_CURSADA);

        var cursada = mapper.toDomain(inscripcion,
                List.of(elementoPlan(1, 7, 40, (short) 3), elementoPlan(2, 7, 40, (short) 3)), null, null, Map.of());

        assertThat(cursada.getAnioPlan()).isEqualTo(3);
    }

    /*
     * protects: una propuesta sin entidad o un anio de cursada nulo no rompen el mapeo.
     * fails_when: propuestaNombre o anioCursada nulos lanzan NullPointerException.
     * no_claim: no prueba que la base pueda devolver propuesta nula.
     */
    @Test
    void nullablesStayNull() {
        var inscripcion = inscripcion(11, 7, 40, "ACT-1", 4321, 1, "Anual", 345, null);

        var cursada = mapper.toDomain(inscripcion, List.of(), null, null, Map.of());

        assertThat(cursada.getPropuestaNombre()).isNull();
        assertThat(cursada.getAnioCursada()).isNull();
        assertThat(cursada.getResponsableAcademica()).isNull();
    }

    /*
     * protects: el caso de Medicina (plan versión 219, DNI 45258117, lectivo 2026). La comisión está en un período
     *            lectivo anual, pero el plan guarda el semestre: 202, 205, 207 y 307 en Semestre 2 (id 5) y 308 en
     *            Semestre 1 (id 4). Los ids salen de sga_elementos_plan de la base de Guaraní.
     * fails_when: el período vuelve a salir de la comisión y todas las materias quedan ANUAL, o periodoNombre
     *             no acompaña al período del plan.
     * no_claim: no prueba que la comisión real esté en un período anual (la base importada no trae sga_periodos).
     */
    @ParameterizedTest
    @CsvSource({
            "04012005-202, 2, 5, SEGUNDO_SEMESTRE, Semestre 2",
            "04012005-205, 2, 5, SEGUNDO_SEMESTRE, Semestre 2",
            "04012005-207, 2, 5, SEGUNDO_SEMESTRE, Semestre 2",
            "04012005-307, 3, 5, SEGUNDO_SEMESTRE, Semestre 2",
            "04012005-308, 3, 4, PRIMER_SEMESTRE, Semestre 1"
    })
    void periodoComesFromThePlanWhenTheComisionIsAnnual(String codigo, short anio, int periodoPlan,
                                                       PeriodoCursada esperado, String nombreEsperado) {
        var inscripcion = inscripcion(11, 219, 40, codigo, 856, 1, "Anual", 5, null);
        var planes = List.of(elementoPlan(1, 219, 40, anio, periodoPlan));

        var cursada = mapper.toDomain(inscripcion, planes, null, null, NOMBRES_PERIODO);

        assertThat(cursada.getPeriodo()).isEqualTo(esperado);
        assertThat(cursada.getPeriodoNombre()).isEqualTo(nombreEsperado);
        assertThat(cursada.getAnioPlan()).isEqualTo((int) anio);
        assertThat(cursada.getPeriodoLectivo()).isEqualTo(856);
    }

    /*
     * protects: si el plan no define un único período, el período y su nombre salen de la comisión, como antes.
     * fails_when: sin filas del plan, con período nulo en el plan o con revisiones en desacuerdo, se devuelve OTRO,
     *             null o el período de una de las revisiones.
     * no_claim: no define cuál revisión es la correcta cuando difieren.
     */
    @Test
    void periodoFallsBackToTheComisionWhenThePlanDoesNotDefineOne() {
        var inscripcion = inscripcion(11, 7, 40, "ACT-1", 4321, 2, "1er Cuatrimestre", 345, ANIO_CURSADA);

        var sinPlan = mapper.toDomain(inscripcion, List.of(), null, null, NOMBRES_PERIODO);
        var nuloEnPlan = mapper.toDomain(inscripcion, List.of(elementoPlan(1, 7, 40, (short) 1, null)), null, null, NOMBRES_PERIODO);
        var enDesacuerdo = mapper.toDomain(inscripcion,
                List.of(elementoPlan(1, 7, 40, (short) 1, 4), elementoPlan(2, 7, 40, (short) 1, 5)), null, null, NOMBRES_PERIODO);

        for (var cursada : List.of(sinPlan, nuloEnPlan, enDesacuerdo)) {
            assertThat(cursada.getPeriodo()).isEqualTo(PeriodoCursada.PRIMER_SEMESTRE);
            assertThat(cursada.getPeriodoNombre()).isEqualTo("1er Cuatrimestre");
        }
    }

    /*
     * protects: un período del plan sin traducción (por ejemplo 18 Mensual) se respeta como OTRO, y un id del plan
     *            sin nombre conocido deja periodoNombre en null sin romper el mapeo.
     * fails_when: el período del plan fuera del mapa cae a la comisión, o un nombre faltante lanza excepción.
     * no_claim: no decide cómo mostrar OTRO en el core.
     */
    @Test
    void untranslatedPlanPeriodIsOtroAndAMissingNameIsNull() {
        var inscripcion = inscripcion(11, 219, 40, "ACT-6", 856, 1, "Anual", 5, null);

        var mensual = mapper.toDomain(inscripcion, List.of(elementoPlan(1, 219, 40, (short) 6, 18)), null, null, NOMBRES_PERIODO);
        var sinNombre = mapper.toDomain(inscripcion, List.of(elementoPlan(1, 219, 40, (short) 2, 5)), null, null, Map.of());

        assertThat(mensual.getPeriodo()).isEqualTo(PeriodoCursada.OTRO);
        assertThat(mensual.getPeriodoNombre()).isEqualTo("Mensual");
        assertThat(sinNombre.getPeriodo()).isEqualTo(PeriodoCursada.SEGUNDO_SEMESTRE);
        assertThat(sinNombre.getPeriodoNombre()).isNull();
    }

    private static final Map<Integer, String> NOMBRES_PERIODO = Map.of(4, "Semestre 1", 5, "Semestre 2", 18, "Mensual");
}
