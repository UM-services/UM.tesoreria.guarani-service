package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.alumno.application.exception.AlumnoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.alumno.application.service.AlumnoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.dto.AlumnoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.web.mapper.AlumnoGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/alumno")
@RequiredArgsConstructor
@Slf4j
public class AlumnoGuaraniController {

    private final AlumnoGuaraniService service;
    private final AlumnoGuaraniDtoMapper mapper;

    @GetMapping("/{alumno}")
    public ResponseEntity<AlumnoGuaraniResponse> getAlumnoGuarani(@PathVariable Integer alumno) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByAlumnoId(alumno)));
        } catch (AlumnoGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/propuestaTipo/{propuestaTipo}")
    public ResponseEntity<List<AlumnoGuaraniResponse>> getAlumnosByPropuestaTipo(@PathVariable Integer propuestaTipo) {
        return ResponseEntity.ok(service.getAllByPropuestaTipo(propuestaTipo).stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/generate/preuniversitario/test")
    public ResponseEntity<Void> generatePreuniversitarioTest() {
        log.debug("\n\nProcessing AlumnoGuaraniController.generatePreuniversitarioTest\n\n");
        service.processNextInscripcion();
        return ResponseEntity.noContent().build();
    }
}
