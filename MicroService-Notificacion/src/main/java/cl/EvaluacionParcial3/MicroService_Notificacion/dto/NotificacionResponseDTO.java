package cl.EvaluacionParcial3.MicroService_Notificacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionResponseDTO {

    private Long id;
    private Long usuarioId;
    private String tipo;
    private String mensaje;
    private Boolean leida;
    private LocalDateTime fechaCreacion;
}