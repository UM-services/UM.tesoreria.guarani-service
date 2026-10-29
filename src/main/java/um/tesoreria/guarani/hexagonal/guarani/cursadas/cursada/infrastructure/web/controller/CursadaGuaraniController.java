package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.application.service.CursadaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.web.dto.CursadaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.web.mapper.CursadaGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/cursada")
@RequiredArgsConstructor
public class CursadaGuaraniController {

    private final CursadaGuaraniService service;
    private final CursadaGuaraniDtoMapper mapper;

    @GetMapping("/documento/{nroDocumento}/anioAcademico/{anioAcademico}")
    public ResponseEntity<List<CursadaGuaraniResponse>> getByNroDocumentoAndAnioAcademico(
            @PathVariable String nroDocumento,
            @PathVariable Integer anioAcademico) {
        return ResponseEntity.ok(service.getAllByNroDocumentoAndAnioAcademico(nroDocumento, anioAcademico).stream()
                .map(mapper::toResponse)
                .toList());
    }
}
