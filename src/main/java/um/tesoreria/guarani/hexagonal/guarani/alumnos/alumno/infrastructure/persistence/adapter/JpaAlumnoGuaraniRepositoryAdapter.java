package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.out.AlumnoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.mapper.AlumnoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.repository.JpaAlumnoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.repository.JpaPersonaDocumentoGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.repository.JpaPropuestaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.repository.JpaPropuestaAspiraGuaraniRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaAlumnoGuaraniRepositoryAdapter implements AlumnoGuaraniRepository {

    private final JpaAlumnoGuaraniRepository jpaAlumnoGuaraniRepository;
    private final JpaPropuestaGuaraniRepository jpaPropuestaGuaraniRepository;
    private final JpaPropuestaAspiraGuaraniRepository jpaPropuestaAspiraGuaraniRepository;
    private final JpaPersonaDocumentoGuaraniRepository jpaPersonaDocumentoGuaraniRepository;
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
        Set<Integer> propuestas = jpaPropuestaGuaraniRepository.findAll().stream()
                .filter(propuesta -> propuestaTipo.equals(propuesta.getPropuestaTipo()))
                .map(propuesta -> propuesta.getPropuesta())
                .collect(Collectors.toSet());
        var alumnos = jpaAlumnoGuaraniRepository.findAll().stream()
                .filter(alumno -> propuestas.contains(alumno.getPropuesta()))
                .toList();
        return alumnos.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoGuarani> findAllByPropuestaTipoAndFechaInscripcionAfter(Integer propuestaTipo, LocalDate fechaLimite) {
        log.debug("\n\nProcessing JpaAlumnoGuaraniRepositoryAdapter.findAllByPropuestaTipoAndFechaInscripcionAfter\n\n");
        Set<Integer> propuestas = jpaPropuestaGuaraniRepository.findAll().stream()
                .filter(propuesta -> propuestaTipo.equals(propuesta.getPropuestaTipo()))
                .map(propuesta -> propuesta.getPropuesta())
                .collect(Collectors.toSet());
        Set<Integer> propuestasConInscripcion = jpaPropuestaAspiraGuaraniRepository.findAll().stream()
                .filter(aspira -> propuestas.contains(aspira.getPropuesta()))
                .filter(aspira -> aspira.getFechaInscripcion() != null
                        && aspira.getFechaInscripcion().isAfter(fechaLimite))
                .map(aspira -> aspira.getPropuesta())
                .collect(Collectors.toSet());
        return jpaAlumnoGuaraniRepository.findAll().stream()
                .filter(alumno -> propuestasConInscripcion.contains(alumno.getPropuesta()))
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoGuarani> findAllByNroDocumento(String nroDocumento) {
        Set<Integer> personas = jpaPersonaDocumentoGuaraniRepository.findAllByNroDocumento(nroDocumento).stream()
                .map(documento -> documento.getPersona())
                .collect(Collectors.toSet());
        return jpaAlumnoGuaraniRepository.findAllByPersonaIn(personas).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
