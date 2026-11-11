package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.PeriodoCursada;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.ComisionGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.ElementoPlanGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.InscripcionCursadaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.PeriodoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CursadaGuaraniMapper {

    /**
     * El período sale del plan ({@code sga_elementos_plan.periodo_de_cursada}): es donde Guaraní guarda si la
     * actividad es anual o de qué semestre. La comisión puede estar en un período lectivo anual aunque la
     * actividad sea semestral. Si el plan no define un único período, se usa el de la comisión.
     *
     * @param planes          filas de sga_elementos_plan del par exacto (planVersion, elemento) de la inscripción
     * @param nombresPeriodo  nombre de cada período genérico del plan, por id
     */
    public CursadaGuarani toDomain(InscripcionCursadaGuaraniEntity inscripcion,
                                   Collection<ElementoPlanGuaraniEntity> planes,
                                   PropuestaGuaraniEntity propuesta,
                                   Integer responsableAcademica,
                                   Map<Integer, String> nombresPeriodo) {
        if (inscripcion == null) {
            return null;
        }
        ComisionGuaraniEntity comision = inscripcion.getComisionRel();
        PeriodoGuaraniEntity periodoComision = comision.getPeriodoLectivoRel().getPeriodoRel();
        Integer periodoPlan = periodoPlan(planes);
        Integer periodoGenerico = periodoPlan == null ? periodoComision.getPeriodoGenerico() : periodoPlan;
        String periodoNombre = periodoPlan == null
                ? periodoComision.getPeriodoGenericoRel().getNombre()
                : nombresPeriodo.get(periodoPlan);
        return CursadaGuarani.builder()
                .inscripcion(inscripcion.getInscripcion())
                .responsableAcademica(responsableAcademica)
                .propuesta(inscripcion.getAlumnoRel().getPropuesta())
                .propuestaNombre(propuesta == null ? null : propuesta.getNombre())
                .planVersion(inscripcion.getPlanVersion())
                .actividadCodigo(comision.getElementoRel().getCodigo())
                .actividadNombre(comision.getElementoRel().getNombre())
                .anioPlan(anioPlan(planes))
                .periodo(PeriodoCursada.desde(periodoGenerico))
                .periodoLectivo(comision.getPeriodoLectivo())
                .periodoNombre(periodoNombre)
                .anioCursada(inscripcion.getAlumnoRel().getAnioCursada())
                .build();
    }

    /**
     * Devuelve el año del plan solo si todas las filas coinciden en un único valor.
     * Con cero filas, valores distintos o un valor nulo mezclado con otro, devuelve null.
     */
    static Integer anioPlan(Collection<ElementoPlanGuaraniEntity> planes) {
        Short unico = unico(planes, ElementoPlanGuaraniEntity::getAnioDeCursada);
        return Objects.isNull(unico) ? null : Integer.valueOf(unico);
    }

    /**
     * Devuelve el id del período genérico del plan con la misma regla que {@link #anioPlan}.
     */
    static Integer periodoPlan(Collection<ElementoPlanGuaraniEntity> planes) {
        return unico(planes, ElementoPlanGuaraniEntity::getPeriodoDeCursada);
    }

    private static <T> T unico(Collection<ElementoPlanGuaraniEntity> planes, Function<ElementoPlanGuaraniEntity, T> campo) {
        if (planes == null) {
            return null;
        }
        Set<T> valores = planes.stream().map(campo).collect(Collectors.toSet());
        return valores.size() == 1 ? valores.iterator().next() : null;
    }
}
