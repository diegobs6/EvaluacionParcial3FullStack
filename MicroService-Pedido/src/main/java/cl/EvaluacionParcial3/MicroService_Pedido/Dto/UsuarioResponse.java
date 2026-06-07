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
public class UsuarioResponse {
    private Long id;
    private String run;
    private String nombres;
    private String apellidos;
    private LocalDateTime fechaNac;
    private String correo;
    private String direccion;
}
