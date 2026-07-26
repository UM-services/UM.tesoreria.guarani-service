package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import java.util.List;

public interface CreatePreuniversitarioByNroDocumentoUseCase {
    List<AlumnoGuarani> createPreuniversitarioByNroDocumento(String nroDocumento);
}
