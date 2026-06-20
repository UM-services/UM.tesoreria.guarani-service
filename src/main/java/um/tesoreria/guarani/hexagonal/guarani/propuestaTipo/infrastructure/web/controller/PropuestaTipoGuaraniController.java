package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.application.exception.PropuestaTipoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.application.service.PropuestaTipoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.web.dto.PropuestaTipoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.web.mapper.PropuestaTipoGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/propuestaTipo")
@RequiredArgsConstructor
public class PropuestaTipoGuaraniController {

    private final PropuestaTipoGuaraniService service;
    private final PropuestaTipoGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<PropuestaTipoGuaraniResponse>> getAllPropuestaTipos() {
        return ResponseEntity.ok(service.getAllPropuestaTipos().stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{propuestaTipo}")
    public ResponseEntity<PropuestaTipoGuaraniResponse> getPropuestaTipoGuarani(@PathVariable Integer propuestaTipo) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByPropuestaTipo(propuestaTipo)));
        } catch (PropuestaTipoGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
