package cl.EvaluacionParcial3.MicroService_Pedido.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "usuario_id")
    private Long usuarioId;

    @Column(nullable = false, name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false, name = "direccion_envio")
    private String direccionEnvio;
}
