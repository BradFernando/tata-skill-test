package com.bradleycorro.tataskillstest.productos.infrastructure.rest.controller;

import com.bradleycorro.tataskillstest.productos.application.dtos.ProductoRequest;
import com.bradleycorro.tataskillstest.productos.application.dtos.ProductoResponse;
import com.bradleycorro.tataskillstest.productos.domain.models.Producto;
import com.bradleycorro.tataskillstest.productos.domain.ports.in.IProductoUseCaseIn;
import com.bradleycorro.tataskillstest.productos.infrastructure.adapters.mappers.ProductoMapper;
import com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para la gestión de Productos.
 * Expone endpoints idempotentes y consistentes con buenas prácticas HTTP.
 * Todas las respuestas utilizan el contenedor estándar {@link com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api.ApiResponse}.
 */
@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Endpoints para la gestión de productos")
public class ProductoController {

    private final IProductoUseCaseIn productoUseCaseIn;
    private final ProductoMapper productoMapper;

    /**
     * Crea un nuevo producto.
     * @param request DTO con datos validados del producto a crear.
     * @return Respuesta con el producto creado y estado 201.
     */
    @Operation(summary = "Crear un nuevo producto", description = "Permite registrar un producto en el catálogo.")
    @PostMapping
    public ResponseEntity<ApiResponse<ProductoResponse>> crear(@Valid @RequestBody ProductoRequest request) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .build();
        ProductoResponse response = productoMapper.toResponse(productoUseCaseIn.crear(producto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    /**
     * Obtiene un producto por su identificador.
     * @param id identificador del producto.
     * @return 200 con el producto si existe; 404 en caso contrario.
     */
    @Operation(summary = "Obtener producto por ID", description = "Busca un producto específico mediante su identificador único.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoResponse>> obtenerPorId(@PathVariable Long id) {
        return productoUseCaseIn.obtenerPorId(id)
                .map(p -> ResponseEntity.ok(ApiResponse.success(productoMapper.toResponse(p))))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los productos.
     * @return 200 con la lista de productos.
     */
    @Operation(summary = "Listar todos los productos", description = "Recupera una lista de todos los productos registrados.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoResponse>>> obtenerTodos() {
        List<ProductoResponse> response = productoUseCaseIn.obtenerTodos().stream()
                .map(productoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * Actualiza un producto existente.
     * @param id identificador del producto a actualizar.
     * @param request DTO con los datos a modificar.
     * @return 200 con el producto actualizado.
     */
    @Operation(summary = "Actualizar producto", description = "Modifica los datos de un producto existente.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoResponse>> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .build();
        ProductoResponse response = productoMapper.toResponse(productoUseCaseIn.actualizar(id, producto));
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * Elimina un producto por su identificador.
     * @param id identificador del producto a eliminar.
     * @return 204 sin contenido.
     */
    @Operation(summary = "Eliminar producto", description = "Elimina físicamente un producto del sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        productoUseCaseIn.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success(null));
    }
}
