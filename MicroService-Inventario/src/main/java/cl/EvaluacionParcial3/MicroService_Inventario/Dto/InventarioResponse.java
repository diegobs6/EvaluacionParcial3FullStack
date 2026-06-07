package cl.EvaluacionParcial3.MicroService_Inventario.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioResponse {
    private Long id;
    private Long productoId;
    private int stockActual;
    private int stockMin;
    private LocalDateTime ultimaActualizacion;
}
