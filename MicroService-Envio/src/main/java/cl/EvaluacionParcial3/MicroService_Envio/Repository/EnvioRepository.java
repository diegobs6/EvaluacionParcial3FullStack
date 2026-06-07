package cl.EvaluacionParcial3.MicroService_Envio.Repository;

import cl.EvaluacionParcial3.MicroService_Envio.Model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    Optional<Envio> findByNumeroSeguimiento(String numeroSeguimiento);
    List<Envio> findAllByUsuarioId(Long usuarioId);
}
