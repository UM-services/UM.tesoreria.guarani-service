package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in;

public interface CreatePersonalesByNroDocumentoUseCase {

    Boolean createPersonalesByNroDocumento(String nroDocumento);
}
