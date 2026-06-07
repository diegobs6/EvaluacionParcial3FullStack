package cl.EvaluacionParcial3.MicroService_Usuario.Dto;

import jakarta.validation.constraints.NegativeOrZero;
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
    private String nombre;
    private String apellido;
    private LocalDateTime fechaNac;
    private String correo;
    private String direccion;
}
