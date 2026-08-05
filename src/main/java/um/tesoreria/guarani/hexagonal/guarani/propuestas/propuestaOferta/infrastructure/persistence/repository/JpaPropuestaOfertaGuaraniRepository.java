package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.persistence.entity.PropuestaOfertaGuaraniEntity;

@Repository
public interface JpaPropuestaOfertaGuaraniRepository
        extends JpaRepository<PropuestaOfertaGuaraniEntity, PropuestaOfertaGuaraniEntity.CompositeId> {
    List<PropuestaOfertaGuaraniEntity> findAllByUbicacion(Integer ubicacion);

    List<PropuestaOfertaGuaraniEntity> findAllByUbicacionAndPropuestaRel_PropuestaTipo(
            Integer ubicacion, Integer propuestaTipo);
}
