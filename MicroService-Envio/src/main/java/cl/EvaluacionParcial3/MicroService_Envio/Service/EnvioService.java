package cl.EvaluacionParcial3.MicroService_Envio.Service;

import cl.EvaluacionParcial3.MicroService_Envio.Client.PedidoClient;
import cl.EvaluacionParcial3.MicroService_Envio.Client.UsuarioClient;
import cl.EvaluacionParcial3.MicroService_Envio.Dto.EnvioRequest;
import cl.EvaluacionParcial3.MicroService_Envio.Dto.EnvioResponse;
import cl.EvaluacionParcial3.MicroService_Envio.Mapper.EnvioMapper;
import cl.EvaluacionParcial3.MicroService_Envio.Model.Envio;
import cl.EvaluacionParcial3.MicroService_Envio.Repository.EnvioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;
    @Autowired
    private EnvioMapper envioMapper;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private PedidoClient pedidoClient;

    public EnvioResponse crearEnvio(EnvioRequest envioRequest) {
        log.info("Creando nuevo envio para el usuario: {}", envioRequest.getUsuarioId());
        // Validar usuario
        usuarioClient.obtenerUsuarioPorId(envioRequest.getUsuarioId());

        // Validar pedido
        pedidoClient.obtenerPedidoPorId(envioRequest.getPedidoId());

        Envio envio = envioMapper.fromRequest(envioRequest);

        Envio savedEnvio = envioRepository.save(envio);

        return envioMapper.toResponse(savedEnvio);
    }

    public List<EnvioResponse> listarTodos() {
        log.info("Obteniendo todos los envios");
        return envioRepository.findAll()
                .stream()
                .map(envio -> envioMapper.toResponse(envio))
                .collect(Collectors.toList());
    }

    public List<EnvioResponse> obtenerEnvioPorUsuarioId(Long usuarioId) {
        log.info("Obteniendo pedidos para el usuario ID: {}", usuarioId);
        List<Envio> envios = envioRepository.findAllByUsuarioId(usuarioId);
        return envios.stream()
                .map(envioMapper::toResponse)
                .toList();
    }

    public EnvioResponse obtenerEnvioPorNumeroSeguimiento(String numeroSeguimiento) {
        log.info("Buscando envío con número de seguimiento: {}", numeroSeguimiento);

        return envioRepository.findByNumeroSeguimiento(numeroSeguimiento)
                .map(envioMapper::toResponse)
                .orElseThrow(() -> {
                    log.error("No se encontró el envío con número: {}", numeroSeguimiento);
                    return new RuntimeException("Envío no encontrado");
                });
    }

    public EnvioResponse actualizarEnvio(Long id, EnvioRequest envioRequest) {
        log.info("Actualizando envio con ID: {}", id);

        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró envio con ID: {}", id);
                    return new RuntimeException("No existe envio con ID: " + id);
                });

        // Validar que el usuario existe en ms-usuario
        usuarioClient.obtenerUsuarioPorId(envioRequest.getUsuarioId());
        log.debug("Usuario ID {} validado en ms-usuario", envioRequest.getUsuarioId());

        // Validar que el pedido existe en ms-pedido
        pedidoClient.obtenerPedidoPorId(envioRequest.getPedidoId());
        log.debug("Pedido ID {} validado en ms-pedido", envioRequest.getPedidoId());

        envio.setPedidoId(envioRequest.getPedidoId());
        envio.setUsuarioId(envioRequest.getUsuarioId());
        envio.setDireccionDestino(envioRequest.getDireccionDestino());
        envio.setEstado(envioRequest.getEstado());
        envio.setFechaDespacho(envioRequest.getFechaDespacho());
        envio.setFechaEstimadaEntrega(envioRequest.getFechaEstimadaEntrega());
        envio.setEmpresaEnvio(envioRequest.getEmpresaEnvio());
        envio.setNumeroSeguimiento(envioRequest.getNumeroSeguimiento());

        Envio savedEnvio = envioRepository.save(envio);
        log.info("Envio actualizado exitosamente con ID: {}", savedEnvio.getId());

        return envioMapper.toResponse(savedEnvio);
    }

    public void eliminarEnvio(Long id) {
        log.info("Eliminando el envio con ID: {}", id);
        envioRepository.deleteById(id);
    }

}
