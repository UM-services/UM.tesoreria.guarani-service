package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.in.GetPropuestasResponsableAcademicaByResponsableAcademicaAndPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.out.PropuestaResponsableAcademicaGuaraniRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPropuestasResponsableAcademicaByResponsableAcademicaAndPropuestaTipoUseCaseImpl
        implements GetPropuestasResponsableAcademicaByResponsableAcademicaAndPropuestaTipoUseCase {
    private final PropuestaResponsableAcademicaGuaraniRepository repository;

    @Override
    public List<PropuestaResponsableAcademicaGuarani> getByResponsableAcademicaAndPropuestaTipo(
            Integer responsableAcademica, Integer propuestaTipo) {
        return repository.findAllByResponsableAcademicaAndPropuestaTipo(responsableAcademica, propuestaTipo);
    }
}
