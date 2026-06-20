package um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.application.exception.PropuestaGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.application.service.PropuestaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.dto.PropuestaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.web.mapper.PropuestaGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/propuesta")
@RequiredArgsConstructor
public class PropuestaGuaraniController {

    private final PropuestaGuaraniService service;
    private final PropuestaGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<PropuestaGuaraniResponse>> getAllPropuestas() {
        return ResponseEntity.ok(service.getAllPropuestas().stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{propuesta}")
    public ResponseEntity<PropuestaGuaraniResponse> getPropuestaGuarani(@PathVariable Integer propuesta) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByPropuestaId(propuesta)));
        } catch (PropuestaGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
