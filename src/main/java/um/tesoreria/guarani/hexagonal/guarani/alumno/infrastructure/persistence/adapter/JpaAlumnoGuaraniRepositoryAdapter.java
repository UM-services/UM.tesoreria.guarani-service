package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.out.AlumnoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.mapper.AlumnoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.repository.JpaAlumnoGuaraniRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaAlumnoGuaraniRepositoryAdapter implements AlumnoGuaraniRepository {

    private final JpaAlumnoGuaraniRepository jpaAlumnoGuaraniRepository;
    private final AlumnoGuaraniMapper mapper;

    @Override
    public Optional<AlumnoGuarani> findByAlumnoId(Integer alumnoId) {
        return jpaAlumnoGuaraniRepository.findById(alumnoId)
                .map(mapper::toDomain);
    }
}
