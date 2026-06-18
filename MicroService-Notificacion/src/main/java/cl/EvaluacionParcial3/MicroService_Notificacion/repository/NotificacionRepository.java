package cl.EvaluacionParcial3.MicroService_Notificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.EvaluacionParcial3.MicroService_Notificacion.model.Notificacion;
import cl.EvaluacionParcial3.MicroService_Notificacion.model.TipoNotificacion;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long>{

    
    // Lista todas las notificaciones de un usuario
     
    List<Notificacion> findByUsuarioId(Long usuarioId);

    
    // Lista las notificaciones no leídas de un usuario
     
    List<Notificacion> findByUsuarioIdAndLeidaFalse(Long usuarioId);

    
    // Lista las notificaciones leídas de un usuario
     
    List<Notificacion> findByUsuarioIdAndLeidaTrue(Long usuarioId);

    
    // Lista todas las notificaciones por tipo
     
    List<Notificacion> findByTipo(TipoNotificacion tipo);


    // Cuenta las notificaciones no leídas de un usuario
     
    long countByUsuarioIdAndLeidaFalse(Long usuarioId);
}
