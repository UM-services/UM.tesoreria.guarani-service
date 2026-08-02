package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;

import java.util.List;

public interface GetPropuestasResponsableAcademicaByResponsableAcademicaUseCase {
    List<PropuestaResponsableAcademicaGuarani> getByResponsableAcademica(Integer responsableAcademica);
}
