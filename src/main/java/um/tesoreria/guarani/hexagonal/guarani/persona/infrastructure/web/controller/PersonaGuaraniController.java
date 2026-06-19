package um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.persona.application.exception.PersonaGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.persona.application.service.PersonaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.web.dto.PersonaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.web.mapper.PersonaGuaraniDtoMapper;

@RestController
@RequestMapping("/api/tesoreria/guarani/persona")
@RequiredArgsConstructor
public class PersonaGuaraniController {

    private final PersonaGuaraniService service;
    private final PersonaGuaraniDtoMapper mapper;

    @GetMapping("/{persona}")
    public ResponseEntity<PersonaGuaraniResponse> getPersonaGuarani(@PathVariable Integer persona) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByPersonaId(persona)));
        } catch (PersonaGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
