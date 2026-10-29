package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.ComisionGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.ElementoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.ElementoPlanGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.ElementoRevisionGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.InscripcionCursadaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.PeriodoGenericoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.PeriodoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.PeriodoLectivoGuaraniEntity;

import java.math.BigDecimal;

/**
 * Datos sintéticos en memoria para los tests de persistencia. No son datos de Guaraní.
 */
public final class CursadaEntityFixtures {

    private CursadaEntityFixtures() {
    }

    public static InscripcionCursadaGuaraniEntity inscripcion(int id, int planVersion, int elemento, String codigo,
                                                              int periodoLectivo, int periodoGenerico, String nombreGenerico,
                                                              int propuesta, Short anioCursada) {
        var generico = PeriodoGenericoGuaraniEntity.builder().periodoGenerico(periodoGenerico).nombre(nombreGenerico).build();
        var periodo = PeriodoGuaraniEntity.builder().periodo(900 + periodoLectivo).nombre("Período sintético")
                .anioAcademico(BigDecimal.valueOf(2026)).periodoGenerico(periodoGenerico).periodoGenericoRel(generico).build();
        var lectivo = PeriodoLectivoGuaraniEntity.builder().periodoLectivo(periodoLectivo).periodo(periodo.getPeriodo())
                .periodoRel(periodo).build();
        var actividad = ElementoGuaraniEntity.builder().elemento(elemento).codigo(codigo).nombre("Actividad " + codigo).build();
        var comision = ComisionGuaraniEntity.builder().comision(7000 + id).elemento(elemento).periodoLectivo(periodoLectivo)
                .elementoRel(actividad).periodoLectivoRel(lectivo).build();
        var alumno = AlumnoGuaraniEntity.builder().alumno(100 + id).propuesta(propuesta).anioCursada(anioCursada).build();
        return InscripcionCursadaGuaraniEntity.builder().inscripcion(id).comision(comision.getComision()).alumno(alumno.getAlumno())
                .planVersion(planVersion).estado("A").comisionRel(comision).alumnoRel(alumno).build();
    }

    public static ElementoPlanGuaraniEntity elementoPlan(int id, int planVersion, int elemento, Short anioDeCursada) {
        var revision = ElementoRevisionGuaraniEntity.builder().elementoRevision(5000 + id).elemento(elemento).build();
        return ElementoPlanGuaraniEntity.builder().elementoPlan(id).planVersion(planVersion).elementoRevision(revision.getElementoRevision())
                .anioDeCursada(anioDeCursada).elementoRevisionRel(revision).build();
    }
}
