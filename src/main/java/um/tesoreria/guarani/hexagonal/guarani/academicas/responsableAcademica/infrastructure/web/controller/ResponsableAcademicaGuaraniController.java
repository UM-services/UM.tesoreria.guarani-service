package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.application.service.ResponsableAcademicaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.web.dto.ResponsableAcademicaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.web.mapper.ResponsableAcademicaGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/responsableAcademica")
@RequiredArgsConstructor
public class ResponsableAcademicaGuaraniController {

    private final ResponsableAcademicaGuaraniService service;
    private final ResponsableAcademicaGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<ResponsableAcademicaGuaraniResponse>> getAllResponsablesAcademicas() {
        return ResponseEntity.ok(service.getAllResponsablesAcademicas().stream()
                .map(mapper::toResponse)
                .toList());
    }
}
