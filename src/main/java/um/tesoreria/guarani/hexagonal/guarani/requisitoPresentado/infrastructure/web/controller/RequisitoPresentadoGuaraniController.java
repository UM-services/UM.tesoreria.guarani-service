package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.application.exception.RequisitoPresentadoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.application.service.RequisitoPresentadoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.web.dto.RequisitoPresentadoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.web.mapper.RequisitoPresentadoGuaraniDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/tesoreria/guarani/requisitoPresentado")
@RequiredArgsConstructor
public class RequisitoPresentadoGuaraniController {

    private final RequisitoPresentadoGuaraniService service;
    private final RequisitoPresentadoGuaraniDtoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<RequisitoPresentadoGuaraniResponse>> getAllRequisitosPresentados() {
        return ResponseEntity.ok(service.getAllRequisitosPresentados().stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{requisitoPresentado}")
    public ResponseEntity<RequisitoPresentadoGuaraniResponse> getRequisitoPresentado(@PathVariable Integer requisitoPresentado) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByRequisitoPresentadoId(requisitoPresentado)));
        } catch (RequisitoPresentadoGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
