package cl.EvaluacionParcial3.MicroService_Pedido.Repository;

import cl.EvaluacionParcial3.MicroService_Pedido.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    List<Pedido> findAllByUsuarioId(Long usuarioId);
    Optional<Pedido> findByUsuarioId(Long usuarioId);

}
