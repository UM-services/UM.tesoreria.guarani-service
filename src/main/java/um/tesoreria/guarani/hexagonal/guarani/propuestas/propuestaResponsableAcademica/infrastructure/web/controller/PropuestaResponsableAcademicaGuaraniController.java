package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.application.service.PropuestaResponsableAcademicaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.dto.PropuestaResponsableAcademicaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.web.mapper.PropuestaResponsableAcademicaGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/propuestaResponsableAcademica")
@RequiredArgsConstructor
public class PropuestaResponsableAcademicaGuaraniController {
    private final PropuestaResponsableAcademicaGuaraniService service;
    private final PropuestaResponsableAcademicaGuaraniDtoMapper mapper;

    @GetMapping("/responsableAcademica/{responsableAcademica}")
    public ResponseEntity<List<PropuestaResponsableAcademicaGuaraniResponse>> getByResponsableAcademica(
            @PathVariable Integer responsableAcademica) {
        return ResponseEntity.ok(service.getByResponsableAcademica(responsableAcademica).stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/responsableAcademica/preuniversitario/{responsableAcademica}")
    public ResponseEntity<List<PropuestaResponsableAcademicaGuaraniResponse>> getByResponsableAcademicaAndPropuestaTipo(
            @PathVariable Integer responsableAcademica) {
        return ResponseEntity.ok(service.getByResponsableAcademicaAndPropuestaTipo(responsableAcademica, 204).stream()
                .map(mapper::toResponse)
                .toList());
    }
}
