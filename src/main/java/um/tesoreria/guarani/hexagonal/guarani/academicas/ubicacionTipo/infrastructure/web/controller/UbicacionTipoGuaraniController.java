package um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.application.service.UbicacionTipoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.web.dto.UbicacionTipoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.academicas.ubicacionTipo.infrastructure.web.mapper.UbicacionTipoGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/ubicacionTipo")
@RequiredArgsConstructor
public class UbicacionTipoGuaraniController {

    private final UbicacionTipoGuaraniService service;
    private final UbicacionTipoGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<UbicacionTipoGuaraniResponse>> getAllUbicacionTipos() {
        return ResponseEntity.ok(service.getAllUbicacionTipos().stream()
                .map(mapper::toResponse)
                .toList());
    }
}
