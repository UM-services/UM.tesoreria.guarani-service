package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.entity.ResponsableAcademicaGuaraniEntity;

@Repository
public interface JpaResponsableAcademicaGuaraniRepository
        extends JpaRepository<ResponsableAcademicaGuaraniEntity, Integer> {
}
