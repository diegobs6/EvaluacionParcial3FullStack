package cl.EvaluacionParcial3.MicroService_Pedido.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @PastOrPresent(message = "La fecha del pedido no debe ser futura")
    @NotNull(message = "La fecha del pedido es obligatoria")
    private LocalDateTime fechaPedido;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @Positive(message = "El total debe ser mayor a 0")
    private int total;

    @NotBlank(message = "La direccion de envio es obligatoria")
    @Size(max = 250, message = "La direccion no debe ser mayor a 250 caracteres")
    private String direccionEnvio;
}
