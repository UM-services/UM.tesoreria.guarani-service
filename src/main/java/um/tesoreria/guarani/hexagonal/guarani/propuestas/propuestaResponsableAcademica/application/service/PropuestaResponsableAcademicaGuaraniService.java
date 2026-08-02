package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.in.GetPropuestasResponsableAcademicaByResponsableAcademicaAndPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.in.GetPropuestasResponsableAcademicaByResponsableAcademicaUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropuestaResponsableAcademicaGuaraniService {
    private final GetPropuestasResponsableAcademicaByResponsableAcademicaUseCase getByResponsableAcademicaUseCase;
    private final GetPropuestasResponsableAcademicaByResponsableAcademicaAndPropuestaTipoUseCase getByResponsableAcademicaAndPropuestaTipoUseCase;

    public List<PropuestaResponsableAcademicaGuarani> getByResponsableAcademica(Integer responsableAcademica) {
        return getByResponsableAcademicaUseCase.getByResponsableAcademica(responsableAcademica);
    }

    public List<PropuestaResponsableAcademicaGuarani> getByResponsableAcademicaAndPropuestaTipo(
            Integer responsableAcademica, Integer propuestaTipo) {
        return getByResponsableAcademicaAndPropuestaTipoUseCase
                .getByResponsableAcademicaAndPropuestaTipo(responsableAcademica, propuestaTipo);
    }
}
