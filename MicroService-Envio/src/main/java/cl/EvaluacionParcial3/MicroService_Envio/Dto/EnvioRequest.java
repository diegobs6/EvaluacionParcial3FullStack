package cl.EvaluacionParcial3.MicroService_Envio.Dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioRequest {

    @NotNull(message = "El ID del pedido es obligatorio")
    private Long pedidoId;

    @NotNull(message = "El ID del usuario debe ser obligatorio")
    private Long usuarioId;

    @NotBlank(message = "La direccion de el envio es obligatorio")
    private String direccionDestino;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "La fecha de despacho es obligatoria")
    @FutureOrPresent(message = "La fecha de despacho debe ser una fecha presente o futura")
    private LocalDateTime fechaDespacho;

    @NotNull(message = "La fecha estimada de entrega es obligatoria")
    @FutureOrPresent(message = "La fecha estimada de entrega debe ser una fecha presente o futura")
    private LocalDate fechaEstimadaEntrega;

    @Size(max = 150, message = "El nombre de la empresa de envio no debe superar los 150 caracteres")
    private String empresaEnvio;

    @Size(min = 0, max = 15, message = "El numero de seguimiento debe ser entre 8 y 15 caracteres")
    @NotBlank
    private String numeroSeguimiento;
}