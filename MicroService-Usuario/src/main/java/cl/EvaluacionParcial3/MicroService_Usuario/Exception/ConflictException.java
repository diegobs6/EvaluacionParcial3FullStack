package cl.EvaluacionParcial3.MicroService_Usuario.Exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
