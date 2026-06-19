package um.tesoreria.guarani.hexagonal.guarani.propuesta.application.exception;

public class PropuestaGuaraniException extends RuntimeException {
    public PropuestaGuaraniException(Integer propuestaId) {
        super("Could not find propuesta with id: " + propuestaId);
    }
}
