package cl.EvaluacionParcial3.MicroService_Envio.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnvioResponse {

    private Long id;

    private Long pedidoId;

    private Long usuarioId;

    private String direccionDestino;

    private String estado;

    private LocalDateTime fechaDespacho;

    private LocalDate fechaEstimadaEntrega;

    private String empresaEnvio;

    private String numeroSeguimiento;
}
