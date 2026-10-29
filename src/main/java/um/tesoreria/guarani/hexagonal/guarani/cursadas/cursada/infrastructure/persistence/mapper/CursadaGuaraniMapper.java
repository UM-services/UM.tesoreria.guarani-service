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
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CursadaGuaraniMapper {

    /**
     * @param planes filas de sga_elementos_plan del par exacto (planVersion, elemento) de la inscripción
     */
    public CursadaGuarani toDomain(InscripcionCursadaGuaraniEntity inscripcion,
                                   Collection<ElementoPlanGuaraniEntity> planes,
                                   PropuestaGuaraniEntity propuesta,
                                   Integer responsableAcademica) {
        if (inscripcion == null) {
            return null;
        }
        ComisionGuaraniEntity comision = inscripcion.getComisionRel();
        PeriodoGuaraniEntity periodo = comision.getPeriodoLectivoRel().getPeriodoRel();
        return CursadaGuarani.builder()
                .inscripcion(inscripcion.getInscripcion())
                .responsableAcademica(responsableAcademica)
                .propuesta(inscripcion.getAlumnoRel().getPropuesta())
                .propuestaNombre(propuesta == null ? null : propuesta.getNombre())
                .planVersion(inscripcion.getPlanVersion())
                .actividadCodigo(comision.getElementoRel().getCodigo())
                .actividadNombre(comision.getElementoRel().getNombre())
                .anioPlan(anioPlan(planes))
                .periodo(PeriodoCursada.desde(periodo.getPeriodoGenerico()))
                .periodoLectivo(comision.getPeriodoLectivo())
                .periodoNombre(periodo.getPeriodoGenericoRel().getNombre())
                .anioCursada(inscripcion.getAlumnoRel().getAnioCursada())
                .build();
    }

    /**
     * Devuelve el año del plan solo si todas las filas coinciden en un único valor.
     * Con cero filas, valores distintos o un valor nulo mezclado con otro, devuelve null.
     */
    static Integer anioPlan(Collection<ElementoPlanGuaraniEntity> planes) {
        if (planes == null) {
            return null;
        }
        var valores = planes.stream()
                .map(ElementoPlanGuaraniEntity::getAnioDeCursada)
                .collect(Collectors.toSet());
        if (valores.size() != 1) {
            return null;
        }
        Short unico = valores.iterator().next();
        return Objects.isNull(unico) ? null : Integer.valueOf(unico);
    }
}
