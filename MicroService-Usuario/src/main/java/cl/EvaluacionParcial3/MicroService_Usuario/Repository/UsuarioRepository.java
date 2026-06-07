package cl.EvaluacionParcial3.MicroService_Usuario.Repository;

import cl.EvaluacionParcial3.MicroService_Usuario.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByRun(String run);
    Optional<Usuario> findByRun(String run);
}
