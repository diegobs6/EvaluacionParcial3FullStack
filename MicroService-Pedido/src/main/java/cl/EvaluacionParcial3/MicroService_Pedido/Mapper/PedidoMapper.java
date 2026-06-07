package cl.EvaluacionParcial3.MicroService_Pedido.Mapper;

import cl.EvaluacionParcial3.MicroService_Pedido.Dto.PedidoRequest;
import cl.EvaluacionParcial3.MicroService_Pedido.Dto.PedidoResponse;
import cl.EvaluacionParcial3.MicroService_Pedido.Model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public Pedido fromRequest(PedidoRequest request) {
        return Pedido.builder()
                .usuarioId(request.getUsuarioId())
                .fechaPedido(request.getFechaPedido())
                .estado(request.getEstado())
                .total(request.getTotal())
                .direccionEnvio(request.getDireccionEnvio())
                .build();
    }

    public PedidoResponse toResponse(Pedido pedido) {
        return PedidoResponse.builder()
                .id(pedido.getId())
                .usuarioId(pedido.getUsuarioId())
                .fechaPedido(pedido.getFechaPedido())
                .estado(pedido.getEstado())
                .total(pedido.getTotal())
                .direccionEnvio(pedido.getDireccionEnvio())
                .build();
    }
}
