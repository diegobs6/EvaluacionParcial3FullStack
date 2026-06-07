package cl.EvaluacionParcial3.MicroService_Inventario.Exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
