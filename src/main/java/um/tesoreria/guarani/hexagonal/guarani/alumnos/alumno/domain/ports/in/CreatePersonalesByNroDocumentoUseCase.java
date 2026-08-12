package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;

import java.util.List;

public interface CreatePersonalesByNroDocumentoUseCase {

    List<CreatePersonalesResponse> createPersonalesByNroDocumento(String nroDocumento);
}
