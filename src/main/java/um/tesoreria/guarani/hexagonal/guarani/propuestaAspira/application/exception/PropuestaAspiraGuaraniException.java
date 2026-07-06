package um.tesoreria.guarani.hexagonal.guarani.propuestaAspira.application.exception;

public class PropuestaAspiraGuaraniException extends RuntimeException {
    public PropuestaAspiraGuaraniException(Integer propuestaAspiraId) {
        super("Could not find propuestaAspira with id: " + propuestaAspiraId);
    }
}
