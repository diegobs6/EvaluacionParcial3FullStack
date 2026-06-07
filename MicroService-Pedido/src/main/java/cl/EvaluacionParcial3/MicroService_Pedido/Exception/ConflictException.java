package cl.EvaluacionParcial3.MicroService_Pedido.Exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
