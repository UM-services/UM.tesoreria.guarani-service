package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.application.exception.PersonaDocumentoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.application.service.PersonaDocumentoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.web.dto.PersonaDocumentoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.web.mapper.PersonaDocumentoGuaraniDtoMapper;

@RestController
@RequestMapping("/api/tesoreria/guarani/personaDocumento")
@RequiredArgsConstructor
public class PersonaDocumentoGuaraniController {

    private final PersonaDocumentoGuaraniService service;
    private final PersonaDocumentoGuaraniDtoMapper mapper;

    @GetMapping("/{documento}")
    public ResponseEntity<PersonaDocumentoGuaraniResponse> getPersonaDocumentoGuarani(@PathVariable Integer documento) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByDocumento(documento)));
        } catch (PersonaDocumentoGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
