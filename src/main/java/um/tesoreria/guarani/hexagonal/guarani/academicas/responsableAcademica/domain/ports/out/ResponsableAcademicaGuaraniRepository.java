package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.ports.out;

import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.model.ResponsableAcademicaGuarani;

import java.util.List;

public interface ResponsableAcademicaGuaraniRepository {

    List<ResponsableAcademicaGuarani> findAll();
}
