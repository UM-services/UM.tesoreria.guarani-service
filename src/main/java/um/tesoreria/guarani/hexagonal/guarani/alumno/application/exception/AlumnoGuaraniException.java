package um.tesoreria.guarani.hexagonal.guarani.alumno.application.exception;

public class AlumnoGuaraniException extends RuntimeException {
    public AlumnoGuaraniException(Integer alumnoId) {
        super("Could not find alumno with id: " + alumnoId);
    }
}
