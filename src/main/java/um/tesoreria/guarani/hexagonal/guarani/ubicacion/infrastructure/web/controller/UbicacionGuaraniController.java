package um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.application.exception.UbicacionGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.application.service.UbicacionGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.web.dto.UbicacionGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.web.mapper.UbicacionGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/ubicacion")
@RequiredArgsConstructor
public class UbicacionGuaraniController {

    private final UbicacionGuaraniService service;
    private final UbicacionGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<UbicacionGuaraniResponse>> getAllUbicaciones() {
        return ResponseEntity.ok(service.getAllUbicaciones().stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{ubicacion}")
    public ResponseEntity<UbicacionGuaraniResponse> getUbicacionGuarani(@PathVariable Integer ubicacion) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByUbicacionId(ubicacion)));
        } catch (UbicacionGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
