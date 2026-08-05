package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.application.service.PropuestaOfertaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.web.dto.PropuestaOfertaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.web.mapper.PropuestaOfertaGuaraniDtoMapper;

@RestController
@RequestMapping("/api/tesoreria/guarani/propuestaOferta")
@RequiredArgsConstructor
public class PropuestaOfertaGuaraniController {
    private final PropuestaOfertaGuaraniService service;
    private final PropuestaOfertaGuaraniDtoMapper mapper;

    @GetMapping("/ubicacion/{ubicacion}")
    public ResponseEntity<List<PropuestaOfertaGuaraniResponse>> getByUbicacion(
            @PathVariable Integer ubicacion) {
        return ResponseEntity.ok(service.getByUbicacion(ubicacion).stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/ubicacion/{ubicacion}/propuestaTipo/204")
    public ResponseEntity<List<PropuestaOfertaGuaraniResponse>> getByUbicacionPropuestaTipo204(
            @PathVariable Integer ubicacion) {
        return ResponseEntity.ok(service.getByUbicacionPropuestaTipo204(ubicacion).stream()
                .map(mapper::toResponse)
                .toList());
    }
}
