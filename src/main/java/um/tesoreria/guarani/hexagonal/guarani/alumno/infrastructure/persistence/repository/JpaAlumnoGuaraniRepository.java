package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;

@Repository
public interface JpaAlumnoGuaraniRepository extends JpaRepository<AlumnoGuaraniEntity, Integer> {
}
