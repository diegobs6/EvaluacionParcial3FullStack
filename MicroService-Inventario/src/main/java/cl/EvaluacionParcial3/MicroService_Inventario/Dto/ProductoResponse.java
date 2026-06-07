package cl.EvaluacionParcial3.MicroService_Inventario.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private Long CategoriaId;
    public boolean activo;
    private LocalDate fechaCreacion;
}
