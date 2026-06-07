package cl.EvaluacionParcial3.MicroService_Usuario.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotBlank(message = "El run es obligatorio")
    @Size(min = 8, max = 13, message = "El run debe tener entre 8 y 13 caracteres")
    private String run;

    @NotBlank(message = "Los nombres son obligatorio")
    @Size(min = 2, max = 150, message = "Los nombres deben tener entre 2 y 150 caracteres")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorio")
    @Size(min = 2, max = 150, message = "Los apellidos deben tener entre 2 y 150 caracteres")
    private String apellido;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    private LocalDateTime fechaNac;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser valido")
    @Size(max = 150, message = "El correo deben tener entre 2 y 150 caracteres")
    private String correo;

    @NotBlank(message = "La direccion es obligatorio")
    @Size(max = 300, message = "La direccion deben tener como maximo 300 caracteres")
    private String direccion;
}
