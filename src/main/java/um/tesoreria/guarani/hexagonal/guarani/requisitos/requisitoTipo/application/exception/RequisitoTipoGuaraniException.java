package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoTipo.application.exception;

public class RequisitoTipoGuaraniException extends RuntimeException {
    public RequisitoTipoGuaraniException(Integer requisitoTipo) {
        super("Could not find requisitoTipo with id: " + requisitoTipo);
    }
}
