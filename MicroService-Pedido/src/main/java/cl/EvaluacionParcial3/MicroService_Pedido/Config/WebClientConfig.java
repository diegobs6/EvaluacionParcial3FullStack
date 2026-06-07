package cl.EvaluacionParcial3.MicroService_Pedido.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${usuario.service.url}")
    private String usuarioUrl;

    @Bean
    public WebClient webClientUsuario() {
        return WebClient.builder()
                .baseUrl(usuarioUrl)
                .build();
    }

}