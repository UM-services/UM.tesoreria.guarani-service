package um.tesoreria.guarani.hexagonal.guarani.persona.application.exception;

public class PersonaGuaraniException extends RuntimeException {
    public PersonaGuaraniException(Integer personaId) {
        super("Could not find persona with id: " + personaId);
    }
}
