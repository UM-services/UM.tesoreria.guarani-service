package um.tesoreria.guarani.hexagonal.guarani.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.CheckAllToUnmarkSendedUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.GetAlumnosByPropuestaTipoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.ProcessNextPreuniversitarioUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.AlumnoGuaraniClient;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.dto.AlumnoDeteccionRequest;
import um.tesoreria.guarani.util.Jsonifier;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProcessNextPreuniversitarioUseCaseImpl implements ProcessNextPreuniversitarioUseCase {

    private static final int PROPUESTA_TIPO_PREUNIVERSITARIO = 204;

    private final GetAlumnosByPropuestaTipoUseCase getAlumnosByPropuestaTipoUseCase;
    private final AlumnoGuaraniClient alumnoGuaraniClient;
    private final CheckAllToUnmarkSendedUseCase checkAllToUnmarkSendedUseCase;

    @Override
    public void processNextPreuniversitario() {
        log.debug("\n\nProcessing ProcessNextPreuniversitarioUseCaseImpl.processNextPreuniversitario\n\n");
        List<AlumnoGuarani> alumnos = getAlumnosByPropuestaTipoUseCase.getByPropuestaTipo(PROPUESTA_TIPO_PREUNIVERSITARIO);
        // Filtrar los alumnos que ya tengan chequera del pre
        List<AlumnoDeteccionRequest> encontrados = new ArrayList<>();
        for (var alumno : alumnos) {
            encontrados.add(AlumnoDeteccionRequest.builder()
                    .ubicacion(alumno.getUbicacion())
                    .propuesta(alumno.getPropuesta())
                    .nroDocumento(alumno.getPersonaRel().getDocumentoPrincipalRel().getNroDocumento())
                    .tipoDocumento(alumno.getPersonaRel().getDocumentoPrincipalRel().getTipoDocumentoRel().getTipoDocumento())
                    .pendiente(true)
                    .build());
        }
        log.debug("Encontrados -> {}", Jsonifier.builder(encontrados).build());
        List<AlumnoDeteccionRequest> pendientes = checkAllToUnmarkSendedUseCase.checkAllAlumnosWithoutChequera(encontrados);
        log.debug("Pendientes -> {}", Jsonifier.builder(pendientes).build());
        // Construir un proceso que genere las chequeras de los alumnos "nuevos"
        var alumno = alumnos.getFirst();
        log.debug("Alumno -> {}", alumno.jsonify());
//        alumnoGuaraniClient.createPreuniversitario(alumno);
    }

}
