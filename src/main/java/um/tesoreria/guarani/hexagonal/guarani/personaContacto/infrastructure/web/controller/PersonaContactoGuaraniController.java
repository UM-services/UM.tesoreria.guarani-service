package um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.application.exception.PersonaContactoGuaraniException;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.application.service.PersonaContactoGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.web.dto.PersonaContactoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.web.mapper.PersonaContactoGuaraniDtoMapper;

@RestController
@RequestMapping("/api/tesoreria/guarani/personaContacto")
@RequiredArgsConstructor
public class PersonaContactoGuaraniController {

    private final PersonaContactoGuaraniService service;
    private final PersonaContactoGuaraniDtoMapper mapper;

    @GetMapping("/{personaContacto}")
    public ResponseEntity<PersonaContactoGuaraniResponse> getPersonaContactoGuarani(@PathVariable Integer personaContacto) {
        try {
            return ResponseEntity.ok(mapper.toResponse(service.getByPersonaContacto(personaContacto)));
        } catch (PersonaContactoGuaraniException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
