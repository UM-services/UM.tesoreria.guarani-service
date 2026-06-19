package um.tesoreria.guarani.hexagonal.guarani.ubicacion.application.exception;

public class UbicacionGuaraniException extends RuntimeException {
    public UbicacionGuaraniException(Integer ubicacionId) {
        super("Could not find ubicacion with id: " + ubicacionId);
    }
}
