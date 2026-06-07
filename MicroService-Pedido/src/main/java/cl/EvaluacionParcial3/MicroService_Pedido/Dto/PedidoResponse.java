package cl.EvaluacionParcial3.MicroService_Pedido.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponse {

    private Long id;
    private Long usuarioId;
    private LocalDateTime fechaPedido;
    private String estado;
    private int total;
    private String direccionEnvio;
}
