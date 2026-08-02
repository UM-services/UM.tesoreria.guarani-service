package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.in.GetPropuestasResponsableAcademicaByResponsableAcademicaUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.out.PropuestaResponsableAcademicaGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPropuestasResponsableAcademicaByResponsableAcademicaUseCaseImpl implements GetPropuestasResponsableAcademicaByResponsableAcademicaUseCase {
    private final PropuestaResponsableAcademicaGuaraniRepository repository;

    @Override
    public List<PropuestaResponsableAcademicaGuarani> getByResponsableAcademica(Integer responsableAcademica) {
        return repository.findAllByResponsableAcademica(responsableAcademica);
    }
}
