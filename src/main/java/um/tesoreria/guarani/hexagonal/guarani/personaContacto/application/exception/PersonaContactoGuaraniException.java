package um.tesoreria.guarani.hexagonal.guarani.personaContacto.application.exception;

public class PersonaContactoGuaraniException extends RuntimeException {
    public PersonaContactoGuaraniException(Integer personaContacto) {
        super("Could not find personaContacto with id: " + personaContacto);
    }
}
