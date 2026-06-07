package cl.EvaluacionParcial3.MicroService_Inventario.Client;

import cl.EvaluacionParcial3.MicroService_Inventario.Dto.ProductoResponse;
import cl.EvaluacionParcial3.MicroService_Inventario.Exception.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.NoSuchElementException;

@Component
@Slf4j
public class ProductoClient {

    @Autowired
    private WebClient webClientProducto;

    public ProductoResponse obtenerProductoPorId(Long productoId) {

        log.info("Consultando producto ID: {} en ms-productos", productoId);

        try {
            return webClientProducto.get()
                    .uri("/{id}", productoId)
                    .retrieve()
                    .bodyToMono(ProductoResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {

            log.error("Error al obtener infomacion del producto con ID: {}", productoId, ex);

            switch (ex.getStatusCode().value()) {
                case 400 ->throw new IllegalArgumentException(
                  "Solicitud invalida al servicio de productos"
                );

                case 403 -> throw new ForbiddenException(

                        "Acceso prohibido al servicio de productos");

                case 404 -> throw new NoSuchElementException(
                        "Producto no encontrado con ID: " + productoId);

                case 500 -> throw new RuntimeException(
                        "Servicio de productos no disponible"
                );

                default -> throw new RuntimeException(
                        "Error interno del servicio de productos");
            }
        }
    }
}
