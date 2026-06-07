package cl.EvaluacionParcial3.MicroService_Inventario.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioRequest {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "El stock actual de un producto es obligatorio")
    @Min(value = 0, message = "El stock actual no puede ser menor a cero")
    private int stockActual;

    @NotNull(message = "El stock minimo de un producto es obligatorio")
    @Min(value = 1, message = "El stock mínimo no puede ser menor a 1")
    private int stockMin;

    @NotNull(message = "La fecha de actualización es obligatoria")
    @PastOrPresent(message = "La fecha de actualización no puede ser futura")
    private LocalDateTime ultimaActualizacion;
}

