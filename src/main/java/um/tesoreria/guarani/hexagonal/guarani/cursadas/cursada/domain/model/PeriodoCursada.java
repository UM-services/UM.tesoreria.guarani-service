package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model;

import java.util.Map;

/**
 * Período de dictado de una cursada, traducido desde el período genérico de Guaraní.
 */
public enum PeriodoCursada {
    ANUAL,
    PRIMER_SEMESTRE,
    SEGUNDO_SEMESTRE,
    OTRO;

    // Ids de sga_periodos_genericos: 1 Anual, 103 Cursada Regular, 2 1er Cuatrimestre, 4 Semestre 1, 3 2do Cuatrimestre, 5 Semestre 2.
    private static final Map<Integer, PeriodoCursada> POR_PERIODO_GENERICO = Map.of(
            1, ANUAL,
            103, ANUAL,
            2, PRIMER_SEMESTRE,
            4, PRIMER_SEMESTRE,
            3, SEGUNDO_SEMESTRE,
            5, SEGUNDO_SEMESTRE
    );

    public static PeriodoCursada desde(Integer periodoGenerico) {
        if (periodoGenerico == null) {
            return OTRO;
        }
        return POR_PERIODO_GENERICO.getOrDefault(periodoGenerico, OTRO);
    }
}
