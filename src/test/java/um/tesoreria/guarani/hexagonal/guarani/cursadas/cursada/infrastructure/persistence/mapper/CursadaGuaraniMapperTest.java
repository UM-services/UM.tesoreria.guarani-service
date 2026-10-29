package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.mapper;

import org.junit.jupiter.api.Test;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.PeriodoCursada;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;

import java.util.List;

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

        var cursada = mapper.toDomain(inscripcion, List.of(elementoPlan(1, 7, 40, (short) 1)), propuesta, 12);

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

        assertThat(mapper.toDomain(inscripcion, List.of(), null, null).getAnioPlan()).isNull();
        assertThat(mapper.toDomain(inscripcion,
                List.of(elementoPlan(1, 7, 40, (short) 1), elementoPlan(2, 7, 40, (short) 2)), null, null).getAnioPlan()).isNull();
        assertThat(mapper.toDomain(inscripcion,
                List.of(elementoPlan(1, 7, 40, (short) 1), elementoPlan(2, 7, 40, null)), null, null).getAnioPlan()).isNull();
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
                List.of(elementoPlan(1, 7, 40, (short) 3), elementoPlan(2, 7, 40, (short) 3)), null, null);

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

        var cursada = mapper.toDomain(inscripcion, List.of(), null, null);

        assertThat(cursada.getPropuestaNombre()).isNull();
        assertThat(cursada.getAnioCursada()).isNull();
        assertThat(cursada.getResponsableAcademica()).isNull();
    }
}
