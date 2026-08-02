package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;

import java.util.List;

public interface GetPropuestasResponsableAcademicaByResponsableAcademicaAndPropuestaTipoUseCase {
    List<PropuestaResponsableAcademicaGuarani> getByResponsableAcademicaAndPropuestaTipo(
            Integer responsableAcademica, Integer propuestaTipo);
}
