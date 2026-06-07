package cl.EvaluacionParcial3.MicroService_Envio.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "pedido_id")
    private Long pedidoId;

    @Column(nullable = false, name = "usuario_id")
    private Long usuarioId;

    @Column(nullable = false, name = "direccion_envio")
    private String direccionDestino;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false, name = "fecha_despacho")
    private LocalDateTime fechaDespacho;

    @Column(nullable = false, name = "fecha_estimada_entrega")
    private LocalDate fechaEstimadaEntrega;

    @Column(nullable = false, name = "empresa_envio")
    private String empresaEnvio;

    @Column(nullable = false, name = "numero_seguimiento")
    private String numeroSeguimiento;
}