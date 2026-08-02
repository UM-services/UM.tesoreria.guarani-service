package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.model.ResponsableAcademicaGuarani;

import java.util.List;

public interface GetAllResponsablesAcademicasUseCase {

    List<ResponsableAcademicaGuarani> getAll();
}
