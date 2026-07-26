package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.exception.PropuestaAspiraGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.application.service.PropuestaAspiraGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.web.dto.PropuestaAspiraGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.web.mapper.PropuestaAspiraGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/propuestaAspira")
@RequiredArgsConstructor
public class PropuestaAspiraGuaraniController {

    private final PropuestaAspiraGuaraniService service;
    private final PropuestaAspiraGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<PropuestaAspiraGuaraniResponse>> getAllPropuestaAspiras() {
        return ResponseEntity.ok(service.getAllPropuestaAspiras().stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{propuestaAspira}")
    public ResponseEntity<PropuestaAspiraGuaraniResponse> getPropuestaAspiraGuarani(@PathVariable Integer propuestaAspira) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByPropuestaAspiraId(propuestaAspira)));
        } catch (PropuestaAspiraGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
