package cl.EvaluacionParcial3.MicroService_Envio.Client;

import cl.EvaluacionParcial3.MicroService_Envio.Dto.UsuarioResponse;
import cl.EvaluacionParcial3.MicroService_Envio.Exception.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.NoSuchElementException;

@Component
@Slf4j
public class UsuarioClient {
    @Autowired
    private WebClient webClientUsuario;

    public UsuarioResponse obtenerUsuarioPorId(Long id) {

        log.info("Obteniendo informacion del usuario con ID: {}", id);

        try {

            return webClientUsuario.get()
                    .uri("/{id}", id)
                    .retrieve()
                    .bodyToMono(UsuarioResponse.class)
                    .block();

        } catch (WebClientResponseException ex) {

            log.error("Error al obtener informacion del usuario con ID: {}", id, ex);

            switch (ex.getStatusCode().value()) {

                case 403 -> throw new ForbiddenException(
                        "Acceso prohibido al servicio de usuarios");

                case 404 -> throw new NoSuchElementException(
                        "Usuario no encontrado con ID: " + id);

                default -> throw new RuntimeException(
                        "Error interno del servicio de usuarios");
            }
        }
    }

}

