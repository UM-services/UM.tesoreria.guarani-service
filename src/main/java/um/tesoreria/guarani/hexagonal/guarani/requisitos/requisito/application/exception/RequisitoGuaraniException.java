package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.application.exception;

public class RequisitoGuaraniException extends RuntimeException {
    public RequisitoGuaraniException(Integer requisitoId) {
        super("Could not find requisito with id: " + requisitoId);
    }
}
