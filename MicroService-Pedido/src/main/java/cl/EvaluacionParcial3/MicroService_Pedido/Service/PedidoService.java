package cl.EvaluacionParcial3.MicroService_Pedido.Service;

import cl.EvaluacionParcial3.MicroService_Pedido.Client.UsuarioClient;
import cl.EvaluacionParcial3.MicroService_Pedido.Dto.PedidoRequest;
import cl.EvaluacionParcial3.MicroService_Pedido.Dto.PedidoResponse;
import cl.EvaluacionParcial3.MicroService_Pedido.Dto.UsuarioResponse;
import cl.EvaluacionParcial3.MicroService_Pedido.Mapper.PedidoMapper;
import cl.EvaluacionParcial3.MicroService_Pedido.Model.Pedido;
import cl.EvaluacionParcial3.MicroService_Pedido.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private UsuarioClient usuarioClient;

    public PedidoResponse crearPedido(PedidoRequest pedidoRequest) {
        log.info("Creando nuevo pedido para el usuario ID: {}",
                pedidoRequest.getUsuarioId());

        UsuarioResponse usuario = usuarioClient.obtenerUsuarioPorId(pedidoRequest.getUsuarioId());

        Pedido pedido = pedidoMapper.fromRequest(pedidoRequest);

        Pedido savedPedido = pedidoRepository.save(pedido);

        return pedidoMapper.toResponse(savedPedido);
    }

    public List<PedidoResponse> obtenerTodosLosPedidos() {
        log.info("Obteniendo todos los pedidos");
        return pedidoRepository.findAll()
                .stream()
                .map(pedido -> pedidoMapper.toResponse(pedido))
                .collect(Collectors.toList());
    }

    public PedidoResponse obtenerPedidoPorId(Long id) {
        log.info("Obteniendo pedido para el id ID: {}",id);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));
        return pedidoMapper.toResponse(pedido);
    }

    public List<PedidoResponse> obtenerPedidosPorUsuarioId(Long usuarioId) {
        log.info("Obteniendo pedidos para el usuario ID: {}", usuarioId);
        List<Pedido> pedidos = pedidoRepository.findAllByUsuarioId(usuarioId);
        return pedidos.stream()
                .map(pedidoMapper::toResponse)
                .toList();
    }

    public PedidoResponse actualizarPedido(Long id, PedidoRequest pedidoRequest) {
        log.info("Actualizando pedido con ID: {}", id);

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró pedido con ID: {}", id);
                    return new RuntimeException("No existe pedido con ID: " + id);
                });

        // Validar que el usuario existe en ms-usuario
        UsuarioResponse usuario = usuarioClient.obtenerUsuarioPorId(pedidoRequest.getUsuarioId());
        log.debug("Usuario ID {} validado en ms-usuario", pedidoRequest.getUsuarioId());

        Pedido pedidoActualizado = pedidoMapper.fromRequest(pedidoRequest);
        pedidoActualizado.setId(pedido.getId());

        Pedido savedPedido = pedidoRepository.save(pedidoActualizado);
        log.info("Pedido actualizado exitosamente con ID: {}", savedPedido.getId());

        return pedidoMapper.toResponse(savedPedido);
    }

    public void eliminarPedido(Long id) {
        log.info("Eliminando el pedido con ID: {}", id);
        pedidoRepository.deleteById(id);
    }
}