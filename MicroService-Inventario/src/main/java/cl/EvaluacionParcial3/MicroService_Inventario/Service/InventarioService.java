package cl.EvaluacionParcial3.MicroService_Inventario.Service;

import cl.EvaluacionParcial3.MicroService_Inventario.Client.ProductoClient;
import cl.EvaluacionParcial3.MicroService_Inventario.Dto.InventarioRequest;
import cl.EvaluacionParcial3.MicroService_Inventario.Dto.InventarioResponse;
import cl.EvaluacionParcial3.MicroService_Inventario.Dto.ProductoResponse;
import cl.EvaluacionParcial3.MicroService_Inventario.Mapper.InventarioMapper;
import cl.EvaluacionParcial3.MicroService_Inventario.Model.Inventario;
import cl.EvaluacionParcial3.MicroService_Inventario.Repository.InventarioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private InventarioMapper inventarioMapper;
    @Autowired
    private ProductoClient productoClient;


    public List<InventarioResponse> listarInventarios() {
        log.info("Obteniendo todos los inventarios");
        return inventarioRepository.findAll()
                .stream()
                .map(inventario -> inventarioMapper.toResponse(inventario))
                .collect(Collectors.toList());
    }

    public InventarioResponse obtenerInventarioPorId(Long id) {
        log.info("Obteniendo inventario para el ID: {}", id);
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Inventario no encontrado"));
        return inventarioMapper.toResponse(inventario);
    }

    public List<InventarioResponse> obtenerInventarioPorProductoId(Long productoId) {
        log.info("Obteniendo inventarios para el producto ID: {}", productoId);
        List<Inventario> inventarios = inventarioRepository.findAllByProductoId(productoId);
        return inventarios.stream()
                .map(inventarioMapper::toResponse)
                .toList();
    }

    public InventarioResponse crearInventario(  InventarioRequest request) {

        log.info("Creando un nuevo inventario");

        ProductoResponse producto = productoClient.obtenerProductoPorId(request.getProductoId());

        if(inventarioRepository.existsByProductoId(request.getProductoId())) {
            throw new IllegalArgumentException("Ya existe un inventario para el producto con ID: " + request.getProductoId());
        }

        Inventario inventario = inventarioMapper.fromRequest(request);

        Inventario savedInventario = inventarioRepository.save(inventario);

        return inventarioMapper.toResponse(savedInventario);
    }


    public InventarioResponse actualizarInventario(Long id, InventarioRequest request) {
        log.info("Actualizando un inventario con el ID: {}",id);
         Inventario inventario = inventarioRepository.findById(id)
                 .orElseThrow(() -> {
                     log.warn("No se encontro el inventario con el ID: {}", id);
                     return new NoSuchElementException("No se encontro el inventario con el ID: " + id);
                 });

         ProductoResponse producto = productoClient.obtenerProductoPorId(request.getProductoId());
         log.debug("Producto ID {} validado en ms-producto", request.getProductoId());

         Inventario inventarioActualizado = inventarioMapper.fromRequest(request);
         inventarioActualizado.setId(inventario.getId());

         Inventario savedInventario = inventarioRepository.save(inventarioActualizado);
         return inventarioMapper.toResponse(savedInventario);
    }



    public void eliminarInventario(Long id) {
        log.info("Eliminando inventario con el ID: {}", id);

        Inventario inventario = inventarioRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("No se encontro el inventario con el ID: " + id));
        inventarioRepository.deleteById(id);
    }
}
