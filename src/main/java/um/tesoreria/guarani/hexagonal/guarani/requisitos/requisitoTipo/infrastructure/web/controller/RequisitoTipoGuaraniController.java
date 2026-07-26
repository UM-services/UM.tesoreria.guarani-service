package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.application.exception.RequisitoTipoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.application.service.RequisitoTipoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.web.dto.RequisitoTipoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.infrastructure.web.mapper.RequisitoTipoGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/requisitoTipo")
@RequiredArgsConstructor
public class RequisitoTipoGuaraniController {

    private final RequisitoTipoGuaraniService service;
    private final RequisitoTipoGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<RequisitoTipoGuaraniResponse>> getAllRequisitoTipos() {
        return ResponseEntity.ok(service.getAllRequisitoTipos().stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{requisitoTipo}")
    public ResponseEntity<RequisitoTipoGuaraniResponse> getRequisitoTipoGuarani(@PathVariable Integer requisitoTipo) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByRequisitoTipo(requisitoTipo)));
        } catch (RequisitoTipoGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
