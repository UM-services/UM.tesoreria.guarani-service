package um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import java.util.List;

public interface GetAlumnosByNroDocumentoUseCase {
    List<AlumnoGuarani> getByNroDocumento(String nroDocumento);
}
