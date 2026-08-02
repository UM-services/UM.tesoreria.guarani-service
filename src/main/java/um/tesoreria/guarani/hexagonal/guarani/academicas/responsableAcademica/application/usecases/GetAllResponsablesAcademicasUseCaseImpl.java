package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.model.ResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.ports.in.GetAllResponsablesAcademicasUseCase;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.ports.out.ResponsableAcademicaGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllResponsablesAcademicasUseCaseImpl implements GetAllResponsablesAcademicasUseCase {

    private final ResponsableAcademicaGuaraniRepository repository;

    @Override
    public List<ResponsableAcademicaGuarani> getAll() {
        return repository.findAll();
    }
}
