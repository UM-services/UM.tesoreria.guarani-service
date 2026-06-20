package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.application.exception;

public class PersonaDocumentoGuaraniException extends RuntimeException {
    public PersonaDocumentoGuaraniException(Integer documento) {
        super("Could not find personaDocumento with documento: " + documento);
    }
}
