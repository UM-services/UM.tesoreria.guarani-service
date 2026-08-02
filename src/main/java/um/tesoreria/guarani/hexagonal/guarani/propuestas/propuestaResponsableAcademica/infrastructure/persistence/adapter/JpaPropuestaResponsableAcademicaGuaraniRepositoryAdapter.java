package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.out.PropuestaResponsableAcademicaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.mapper.PropuestaResponsableAcademicaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.repository.JpaPropuestaResponsableAcademicaGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaPropuestaResponsableAcademicaGuaraniRepositoryAdapter implements PropuestaResponsableAcademicaGuaraniRepository {
    private final JpaPropuestaResponsableAcademicaGuaraniRepository repository;
    private final PropuestaResponsableAcademicaGuaraniMapper mapper;

    @Override
    public List<PropuestaResponsableAcademicaGuarani> findAllByResponsableAcademica(Integer responsableAcademica) {
        return repository.findAllByResponsableAcademica(responsableAcademica).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<PropuestaResponsableAcademicaGuarani> findAllByResponsableAcademicaAndPropuestaTipo(
            Integer responsableAcademica, Integer propuestaTipo) {
        return repository.findAllByResponsableAcademicaAndPropuestaRel_PropuestaTipo(
                        responsableAcademica, propuestaTipo).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
