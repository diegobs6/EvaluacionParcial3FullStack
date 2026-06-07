package cl.EvaluacionParcial3.MicroService_Inventario.Repository;

import cl.EvaluacionParcial3.MicroService_Inventario.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByProductoId(Long productoId);

    List<Inventario> findAllByProductoId(Long productoId);

    boolean existsByProductoId(Long productoId);
}
