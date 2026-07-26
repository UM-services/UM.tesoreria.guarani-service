package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.AlumnoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.mapper.AlumnoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.repository.JpaAlumnoGuaraniByFechaRepository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.repository.JpaAlumnoGuaraniRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaAlumnoGuaraniRepositoryAdapter implements AlumnoGuaraniRepository {

    private final JpaAlumnoGuaraniRepository jpaAlumnoGuaraniRepository;
    private final JpaAlumnoGuaraniByFechaRepository jpaAlumnoGuaraniByFechaRepository;
    private final AlumnoGuaraniMapper mapper;

    @Override
    public Optional<AlumnoGuarani> findByAlumnoId(Integer alumnoId) {
        return jpaAlumnoGuaraniRepository.findById(alumnoId)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoGuarani> findAllByPropuestaTipo(Integer propuestaTipo) {
        log.debug("\n\nProcessing JpaAlumnoGuaraniRepositoryAdapter.findAllByPropuestaTipo\n\n");
        var alumnos = jpaAlumnoGuaraniRepository.findAllByPropuestaTipo(propuestaTipo);
        return alumnos.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoGuarani> findAllByPropuestaTipoAndFechaInscripcionAfter(Integer propuestaTipo, LocalDate fechaLimite) {
        log.debug("\n\nProcessing JpaAlumnoGuaraniRepositoryAdapter.findAllByPropuestaTipoAndFechaInscripcionAfter\n\n");
        return jpaAlumnoGuaraniByFechaRepository
                .findByPropuestaRel_PropuestaTipoAndPropuestaAspiraRel_FechaInscripcionAfter(propuestaTipo, fechaLimite)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoGuarani> findAllByNroDocumento(String nroDocumento) {
        return jpaAlumnoGuaraniRepository.findAllByNroDocumento(nroDocumento).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
