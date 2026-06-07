package cl.EvaluacionParcial3.MicroService_Inventario.Controller;

import cl.EvaluacionParcial3.MicroService_Inventario.Dto.InventarioRequest;
import cl.EvaluacionParcial3.MicroService_Inventario.Dto.InventarioResponse;
import cl.EvaluacionParcial3.MicroService_Inventario.Service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/inventario")
@Slf4j
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/producto/{productoid}")
    @Operation(summary = "Obtener inventario por el id del producto", description = "Obtiene el inventario mediante el id del producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion realizada con exito"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<InventarioResponse> obtenerInventarioPorProductoId(@PathVariable Long productoid) {
        log.info("GET /v1/inventario/producto/{}", productoid);
        return inventarioService.obtenerInventarioPorProductoId(productoid);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener inventario por ID", description = "Obtiene un inventario mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación realizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public InventarioResponse obtenerInventarioPorId(@PathVariable Long id) {
        log.info("GET /v1/inventario/{}", id);
        return inventarioService.obtenerInventarioPorId(id);
    }

    @PostMapping()
    @Operation(summary = "Crear inventario", description = "Crea un nuevo inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado con exito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InventarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "409", description = "El inventario ya existe"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<InventarioResponse> crearInventario(@Valid @RequestBody InventarioRequest inventarioRequest) {
        log.info("POST /v1/inventarios");
        InventarioResponse inventarioResponse = inventarioService.crearInventario(inventarioRequest);
        return new ResponseEntity<>(inventarioResponse, HttpStatus.CREATED);
    }



    @GetMapping
    @Operation(summary = "Obtener inventarios", description = "Obtiene una lista de todos los inventarios registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion realizada con exito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<InventarioResponse>> obtenerInventarios() {
        log.info("GET /v1/inventario");
        List<InventarioResponse> inventarios = inventarioService.listarInventarios();
        return ResponseEntity.ok(inventarios);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inventario", description = "Actualiza un inventario mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion realizada con exito"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<InventarioResponse> actualizarInventario(
            @PathVariable Long id,
            @Valid @RequestBody InventarioRequest inventarioRequest) {
        log.info("PUT /v1/inventario/{}", id);
        InventarioResponse inventarioResponse = inventarioService.actualizarInventario(id, inventarioRequest);
        return ResponseEntity.ok(inventarioResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inventario", description = "Elimina un inventario mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        log.info("DELETE /v1/inventario/{}", id);
        inventarioService.eliminarInventario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
