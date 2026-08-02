package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.entity.PropuestaResponsableAcademicaGuaraniEntity;

import java.util.List;

@Repository
public interface JpaPropuestaResponsableAcademicaGuaraniRepository extends JpaRepository<PropuestaResponsableAcademicaGuaraniEntity, PropuestaResponsableAcademicaGuaraniEntity.CompositeId> {
    List<PropuestaResponsableAcademicaGuaraniEntity> findAllByResponsableAcademica(Integer responsableAcademica);

    List<PropuestaResponsableAcademicaGuaraniEntity> findAllByResponsableAcademicaAndPropuestaRel_PropuestaTipo(
            Integer responsableAcademica, Integer propuestaTipo);
}
