package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.application.exception;

public class RequisitoPresentadoGuaraniException extends RuntimeException {
    public RequisitoPresentadoGuaraniException(Integer requisitoPresentadoId) {
        super("Could not find requisitoPresentado with id: " + requisitoPresentadoId);
    }
}
