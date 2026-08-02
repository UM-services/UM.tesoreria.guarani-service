package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.model.ResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.ports.out.ResponsableAcademicaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.mapper.ResponsableAcademicaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.repository.JpaResponsableAcademicaGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaResponsableAcademicaGuaraniRepositoryAdapter implements ResponsableAcademicaGuaraniRepository {

    private final JpaResponsableAcademicaGuaraniRepository jpaRepository;
    private final ResponsableAcademicaGuaraniMapper mapper;

    @Override
    public List<ResponsableAcademicaGuarani> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
