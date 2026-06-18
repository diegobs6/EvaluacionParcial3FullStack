package cl.EvaluacionParcial3.MicroService_Notificacion.exceptions;

public class NotificacionNotFoundException extends RuntimeException {

    public NotificacionNotFoundException(Long id) {
        super("Notificación no encontrada con ID: " + id);
    }

    public NotificacionNotFoundException(String mensaje) {
        super(mensaje);
    }
}
