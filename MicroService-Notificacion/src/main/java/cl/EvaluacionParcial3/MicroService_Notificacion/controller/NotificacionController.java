package cl.EvaluacionParcial3.MicroService_Notificacion.controller;

import cl.EvaluacionParcial3.MicroService_Notificacion.dto.NotificacionRequestDTO;
import cl.EvaluacionParcial3.MicroService_Notificacion.dto.NotificacionResponseDTO;
import cl.EvaluacionParcial3.MicroService_Notificacion.service.NotificacionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private static final Logger log = LoggerFactory.getLogger(NotificacionController.class);

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

     // Obtiene todas las notificaciones registradas
     
    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> listarTodas() {
        log.info("GET /api/notificaciones - Listar todas las notificaciones");
        List<NotificacionResponseDTO> notificaciones = notificacionService.listarTodas();
        return ResponseEntity.ok(notificaciones);
    }

     // Obtiene una notificación por su ID
     
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/notificaciones/{} - Obtener notificación por ID", id);
        NotificacionResponseDTO notificacion = notificacionService.obtenerPorId(id);
        return ResponseEntity.ok(notificacion);
    }

     // Lista todas las notificaciones de un usuario
     
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NotificacionResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        log.info("GET /api/notificaciones/usuario/{} - Listar notificaciones por usuario", usuarioId);
        List<NotificacionResponseDTO> notificaciones = notificacionService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(notificaciones);
    }

     // Lista las notificaciones no leídas de un usuario
     
    @GetMapping("/usuario/{usuarioId}/no-leidas")
    public ResponseEntity<List<NotificacionResponseDTO>> listarNoLeidas(@PathVariable Long usuarioId) {
        log.info("GET /api/notificaciones/usuario/{}/no-leidas - Listar notificaciones no leídas", usuarioId);
        List<NotificacionResponseDTO> notificaciones = notificacionService.listarNoLeidas(usuarioId);
        return ResponseEntity.ok(notificaciones);
    }

     // Cuenta las notificaciones no leídas de un usuario
     
    @GetMapping("/usuario/{usuarioId}/contador")
    public ResponseEntity<Long> contarNoLeidas(@PathVariable Long usuarioId) {
        log.info("GET /api/notificaciones/usuario/{}/contador - Contar notificaciones no leídas", usuarioId);
        long total = notificacionService.contarNoLeidas(usuarioId);
        return ResponseEntity.ok(total);
    }

     // Lista notificaciones filtradas por tipo
     
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<NotificacionResponseDTO>> listarPorTipo(@PathVariable String tipo) {
        log.info("GET /api/notificaciones/tipo/{} - Listar notificaciones por tipo", tipo);
        List<NotificacionResponseDTO> notificaciones = notificacionService.listarPorTipo(tipo);
        return ResponseEntity.ok(notificaciones);
    }
  
     // Crea una nueva notificación. Valida el body con @Valid (Bean Validation).
     
    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionRequestDTO dto) {
        log.info("POST /api/notificaciones - Crear nueva notificación");
        NotificacionResponseDTO creada = notificacionService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

     // Marca una notificación como leída
     
    @PatchMapping("/{id}/leer")
    public ResponseEntity<NotificacionResponseDTO> marcarComoLeida(@PathVariable Long id) {
        log.info("PATCH /api/notificaciones/{}/leer - Marcar notificación como leída", id);
        NotificacionResponseDTO actualizada = notificacionService.marcarComoLeida(id);
        return ResponseEntity.ok(actualizada);
    }

     // Marca todas las notificaciones de un usuario como leídas
     
    @PatchMapping("/usuario/{usuarioId}/leer-todas")
    public ResponseEntity<Void> marcarTodasComoLeidas(@PathVariable Long usuarioId) {
        log.info("PATCH /api/notificaciones/usuario/{}/leer-todas - Marcar todas como leídas", usuarioId);
        notificacionService.marcarTodasComoLeidas(usuarioId);
        return ResponseEntity.noContent().build();
    }

    
     // Elimina una notificación de forma permanente
     
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/notificaciones/{} - Eliminar notificación", id);
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}