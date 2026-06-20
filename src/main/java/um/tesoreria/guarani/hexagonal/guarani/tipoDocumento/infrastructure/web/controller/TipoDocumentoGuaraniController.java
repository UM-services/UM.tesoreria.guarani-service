package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.application.exception.TipoDocumentoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.application.service.TipoDocumentoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.web.dto.TipoDocumentoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.web.mapper.TipoDocumentoGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/tipoDocumento")
@RequiredArgsConstructor
public class TipoDocumentoGuaraniController {

    private final TipoDocumentoGuaraniService service;
    private final TipoDocumentoGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<TipoDocumentoGuaraniResponse>> getAllTipoDocumentos() {
        return ResponseEntity.ok(service.getAllTipoDocumentos().stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{tipoDocumento}")
    public ResponseEntity<TipoDocumentoGuaraniResponse> getTipoDocumentoGuarani(@PathVariable Short tipoDocumento) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByTipoDocumento(tipoDocumento)));
        } catch (TipoDocumentoGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
