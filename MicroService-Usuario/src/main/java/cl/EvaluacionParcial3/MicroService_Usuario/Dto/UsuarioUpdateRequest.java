package cl.EvaluacionParcial3.MicroService_Usuario.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioUpdateRequest {
    @Size(min = 2, max = 150, message = "Los nombres deben tener entre 2 y 150 caracteres")
    private String nombre;

    @Size(min = 2, max = 150, message = "Los apellidos deben tener entre 2 y 150 caracteres")
    private String apellido;

    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    private LocalDateTime fechaNac;

    @Email(message = "El correo debe ser valido")
    @Size(max = 150, message = "El correo no puede superar los 150 caracteres")
    private String correo;

    @Size(max = 300, message = "La direccion debe tener un maximo de 300 caracteres")
    private String direccion;
}
