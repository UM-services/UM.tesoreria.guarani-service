package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.domain.model.PropuestaResponsableAcademicaGuarani;

import java.util.List;

public interface PropuestaResponsableAcademicaGuaraniRepository {
    List<PropuestaResponsableAcademicaGuarani> findAllByResponsableAcademica(Integer responsableAcademica);

    List<PropuestaResponsableAcademicaGuarani> findAllByResponsableAcademicaAndPropuestaTipo(
            Integer responsableAcademica, Integer propuestaTipo);
}
