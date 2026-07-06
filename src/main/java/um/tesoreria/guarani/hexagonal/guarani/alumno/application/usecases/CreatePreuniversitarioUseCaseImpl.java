package um.tesoreria.guarani.hexagonal.guarani.alumno.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.CheckAllToUnmarkSendedUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.ports.in.CreatePreuniversitarioUseCase;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.AlumnoGuaraniClient;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.dto.AlumnoDeteccionRequest;
import um.tesoreria.guarani.util.Jsonifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePreuniversitarioUseCaseImpl implements CreatePreuniversitarioUseCase {

    private final AlumnoGuaraniClient alumnoGuaraniClient;
    private final CheckAllToUnmarkSendedUseCase checkAllToUnmarkSendedUseCase;

    @Override
    public List<AlumnoGuarani> createPreuniversitario(List<AlumnoGuarani> alumnos) {
        log.debug("\n\nProcessing CreatePreuniversitarioUseCaseImpl.createPreuniversitario\n\n");
        
        if (alumnos == null || alumnos.isEmpty()) {
            return List.of();
        }

        // Filtrar los alumnos que ya tengan chequera del pre
        List<AlumnoDeteccionRequest> encontrados = new ArrayList<>();
        for (var alumno : alumnos) {
            encontrados.add(AlumnoDeteccionRequest.builder()
                    .alumno(alumno.getAlumno())
                    .ubicacion(alumno.getUbicacion())
                    .propuesta(alumno.getPropuesta())
                    .nroDocumento(alumno.getPersonaRel().getDocumentoPrincipalRel().getNroDocumento())
                    .tipoDocumento(alumno.getPersonaRel().getDocumentoPrincipalRel().getTipoDocumentoRel().getTipoDocumento())
                    .pendiente(true)
                    .build());
        }

        List<AlumnoDeteccionRequest> pendientes = checkAllToUnmarkSendedUseCase.checkAllAlumnosWithoutChequera(encontrados);
        var alumnosPendientes = pendientes.stream().map(AlumnoDeteccionRequest::getAlumno).collect(Collectors.toSet());

        List<AlumnoGuarani> filteredAlumnos = new ArrayList<>(alumnos);
        filteredAlumnos.removeIf(alumno -> !alumnosPendientes.contains(alumno.getAlumno()));

        // Separa los que tienen requisito 1024
        var alumnosConRequisito1024 = filteredAlumnos.stream()
                .filter(alumno -> alumno.getPersonaRel() != null
                        && alumno.getPersonaRel().getRequisitosPresentados() != null
                        && alumno.getPersonaRel().getRequisitosPresentados().stream()
                        .anyMatch(rp -> rp.getRequisitoRel() != null && rp.getRequisitoRel().getRequisito() == 1024))
                .toList();
        alumnosConRequisito1024.forEach(alumno -> log.debug("Alumno con promedio -> {}", Jsonifier.builder(alumno.getPersonaRel()).build()));
        filteredAlumnos.removeAll(alumnosConRequisito1024);

        // Construir un proceso que genere las chequeras de los alumnos "nuevos" (limit 50 as default to avoid overload)
        List<AlumnoGuarani> creados = new ArrayList<>();
        for (var alumno : filteredAlumnos.stream().limit(50).toList()) {
            try {
                log.debug("\n\nProcessing Alumno -> {}\n\n", Jsonifier.builder(alumno.getPersonaRel()).build());
                AlumnoGuarani creado = alumnoGuaraniClient.createPreuniversitario(alumno);
                if (creado != null) {
                    creados.add(creado);
                }
            } catch (Exception e) {
                log.error("Error creating preuniversitario for alumno {}: {}", alumno.getAlumno(), e.getMessage());
            }
        }
        return creados;
    }
}
