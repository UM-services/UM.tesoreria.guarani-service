package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.application.exception.RequisitoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.application.service.RequisitoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.web.dto.RequisitoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.web.mapper.RequisitoGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/requisito")
@RequiredArgsConstructor
public class RequisitoGuaraniController {

    private final RequisitoGuaraniService service;
    private final RequisitoGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<RequisitoGuaraniResponse>> getAllRequisitos() {
        return ResponseEntity.ok(service.getAllRequisitos().stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{requisito}")
    public ResponseEntity<RequisitoGuaraniResponse> getRequisitoGuarani(@PathVariable Integer requisito) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByRequisitoId(requisito)));
        } catch (RequisitoGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tipo/{requisitoTipo}")
    public ResponseEntity<List<RequisitoGuaraniResponse>> getRequisitosByTipo(@PathVariable Integer requisitoTipo) {
        return ResponseEntity.ok(service.getByRequisitoTipo(requisitoTipo).stream()
                .map(mapper::toResponse)
                .toList());
    }
}
