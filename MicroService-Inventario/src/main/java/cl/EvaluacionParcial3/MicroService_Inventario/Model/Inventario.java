package cl.EvaluacionParcial3.MicroService_Inventario.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "producto_id")
    private Long productoId;

    @Column(name = "stock_actual", nullable = false)
    private int stockActual;

    @Column(nullable = false)
    private int stockMin;

    @Column(nullable = false, name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
}
