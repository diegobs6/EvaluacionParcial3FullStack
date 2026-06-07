package cl.EvaluacionParcial3.MicroService_Envio.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${usuario.service.url}")
    private String usuarioUrl;

    @Value("${pedido.service.url}")
    private String pedidoUrl;

    @Bean
    public WebClient webClientUsuario() {
        return WebClient.builder()
                .baseUrl(usuarioUrl)
                .build();
    }

    @Bean
    public WebClient webClientPedido() {
        return WebClient.builder()
                .baseUrl(pedidoUrl)
                .build();
    }
}
