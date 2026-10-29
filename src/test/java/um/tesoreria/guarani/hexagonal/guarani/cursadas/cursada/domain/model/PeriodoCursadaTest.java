package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PeriodoCursadaTest {

    /*
     * protects: la traducción de ids de sga_periodos_genericos a ANUAL, PRIMER_SEMESTRE y SEGUNDO_SEMESTRE (decisión D5).
     * fails_when: un id conocido (1, 103, 2, 4, 3, 5) cae en otro valor, o se invierten primer y segundo semestre.
     * no_claim: no prueba que estos ids existan en la base ni que el nombre del período coincida.
     */
    @ParameterizedTest
    @CsvSource({
            "1,ANUAL", "103,ANUAL",
            "2,PRIMER_SEMESTRE", "4,PRIMER_SEMESTRE",
            "3,SEGUNDO_SEMESTRE", "5,SEGUNDO_SEMESTRE"
    })
    void translatesTheKnownGenericPeriodIds(int periodoGenerico, PeriodoCursada esperado) {
        assertThat(PeriodoCursada.desde(periodoGenerico)).isEqualTo(esperado);
    }

    /*
     * protects: los ids sin regla (18 Mensual, 101 Semestral, 102 Cursada Pre y cualquier otro) devuelven OTRO.
     * fails_when: un id desconocido lanza excepción, se asigna a un semestre o se trata como ANUAL.
     * no_claim: no decide si 101 o 102 deberían ser PRIMER_SEMESTRE (se descartó en D5).
     */
    @ParameterizedTest
    @CsvSource({"18", "101", "102", "999"})
    void translatesAnyOtherGenericPeriodIdToOtro(int periodoGenerico) {
        assertThat(PeriodoCursada.desde(periodoGenerico)).isEqualTo(PeriodoCursada.OTRO);
    }

    /*
     * protects: un período genérico nulo no rompe la respuesta.
     * fails_when: desde(null) lanza NullPointerException o devuelve null.
     * no_claim: no prueba que la base pueda devolver null (la columna es NOT NULL).
     */
    @Test
    void translatesNullToOtro() {
        assertThat(PeriodoCursada.desde(null)).isEqualTo(PeriodoCursada.OTRO);
    }
}
