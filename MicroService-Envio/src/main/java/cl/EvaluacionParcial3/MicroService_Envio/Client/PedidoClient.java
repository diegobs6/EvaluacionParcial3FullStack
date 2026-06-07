package cl.EvaluacionParcial3.MicroService_Envio.Client;

import cl.EvaluacionParcial3.MicroService_Envio.Dto.PedidoResponse;
import cl.EvaluacionParcial3.MicroService_Envio.Exception.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.NoSuchElementException;

@Component
@Slf4j
public class PedidoClient {
    @Autowired
    private WebClient webClientPedido;

    public PedidoResponse obtenerPedidoPorId(Long id) {
        log.info("Obteniendo informacion del pedido con ID: {}", id);

        try {

            return webClientPedido.get()
                    .uri("/{id}", id)
                    .retrieve()
                    .bodyToMono(PedidoResponse.class)
                    .block();

        } catch (WebClientResponseException ex) {

            log.error("Error al obtener pedido: {}", id);

            switch (ex.getStatusCode().value()) {
                case 403 -> throw new ForbiddenException(
                        "Acceso prohibido al servicio de pedidos");
                case 404 -> throw new NoSuchElementException(
                        "Pedido no encontrado con el ID: " + id);
                default -> throw new RuntimeException(
                        "Error interno del servicio de pedidos");
            }
        }
    }
}