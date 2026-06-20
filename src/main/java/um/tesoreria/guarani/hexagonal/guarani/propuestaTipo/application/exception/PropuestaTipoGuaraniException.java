package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.application.exception;

public class PropuestaTipoGuaraniException extends RuntimeException {
    public PropuestaTipoGuaraniException(Integer propuestaTipo) {
        super("Could not find propuestaTipo with id: " + propuestaTipo);
    }
}
