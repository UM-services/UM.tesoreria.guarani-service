package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.application.exception;

public class PropuestaGuaraniException extends RuntimeException {
    public PropuestaGuaraniException(Integer propuestaId) {
        super("Could not find propuesta with id: " + propuestaId);
    }
}
