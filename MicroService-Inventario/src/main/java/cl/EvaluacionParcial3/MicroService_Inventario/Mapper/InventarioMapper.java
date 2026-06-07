package cl.EvaluacionParcial3.MicroService_Inventario.Mapper;

import cl.EvaluacionParcial3.MicroService_Inventario.Dto.InventarioRequest;
import cl.EvaluacionParcial3.MicroService_Inventario.Dto.InventarioResponse;
import cl.EvaluacionParcial3.MicroService_Inventario.Model.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public Inventario fromRequest(InventarioRequest request) {
        return Inventario.builder()
                .productoId(request.getProductoId())
                .stockActual(request.getStockActual())
                .stockMin(request.getStockMin())
                .ultimaActualizacion(request.getUltimaActualizacion())
                .build();
    }

    public InventarioResponse toResponse(Inventario inventario) {
        return InventarioResponse.builder()
                .id(inventario.getId())
                .productoId(inventario.getProductoId())
                .stockActual(inventario.getStockActual())
                .stockMin(inventario.getStockMin())
                .ultimaActualizacion(inventario.getUltimaActualizacion())
                .build();
    }
}
