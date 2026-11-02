package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.InscripcionCursadaGuaraniEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface JpaInscripcionCursadaGuaraniRepository extends JpaRepository<InscripcionCursadaGuaraniEntity, Integer> {

    @EntityGraph(attributePaths = {
            "alumnoRel",
            "comisionRel",
            "comisionRel.elementoRel",
            "comisionRel.periodoLectivoRel",
            "comisionRel.periodoLectivoRel.periodoRel",
            "comisionRel.periodoLectivoRel.periodoRel.periodoGenericoRel"
    })
    List<InscripcionCursadaGuaraniEntity> findAllByAlumnoInAndEstadoAndComisionRel_PeriodoLectivoRel_PeriodoRel_AnioAcademico(
            Collection<Integer> alumnos, String estado, BigDecimal anioAcademico);
}
