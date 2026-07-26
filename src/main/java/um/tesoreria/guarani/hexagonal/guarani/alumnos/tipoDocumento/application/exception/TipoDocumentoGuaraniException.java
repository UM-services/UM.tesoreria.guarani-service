package um.tesoreria.guarani.hexagonal.guarani.alumnos.tipoDocumento.application.exception;

public class TipoDocumentoGuaraniException extends RuntimeException {
    public TipoDocumentoGuaraniException(Short tipoDocumento) {
        super("Could not find tipoDocumento with id: " + tipoDocumento);
    }
}
