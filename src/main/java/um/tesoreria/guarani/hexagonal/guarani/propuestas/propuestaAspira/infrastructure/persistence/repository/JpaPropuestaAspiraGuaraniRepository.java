package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.entity.PropuestaAspiraGuaraniEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JpaPropuestaAspiraGuaraniRepository extends JpaRepository<PropuestaAspiraGuaraniEntity, Integer> {
    List<PropuestaAspiraGuaraniEntity> findAllByPropuestaAndUbicacionAndFechaInscripcionGreaterThanEqual(
            Integer propuesta, Integer ubicacion, LocalDate fechaDesde);
}
