package com.bradleycorro.tataskillstest.productos.infrastructure.rest.controller;

import com.bradleycorro.tataskillstest.productos.application.dtos.ProductoRequest;
import com.bradleycorro.tataskillstest.productos.domain.models.Producto;
import com.bradleycorro.tataskillstest.productos.domain.ports.in.IProductoUseCaseIn;
import com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api.ApiResponse;
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
public class ProductoController {

    private final IProductoUseCaseIn productoUseCaseIn;

    /**
     * Crea un nuevo producto.
     * @param request DTO con datos validados del producto a crear.
     * @return Respuesta con el producto creado y estado 201.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Producto>> crear(@Valid @RequestBody ProductoRequest request) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(productoUseCaseIn.crear(producto)));
    }

    /**
     * Obtiene un producto por su identificador.
     * @param id identificador del producto.
     * @return 200 con el producto si existe; 404 en caso contrario.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> obtenerPorId(@PathVariable Long id) {
        return productoUseCaseIn.obtenerPorId(id)
                .map(p -> ResponseEntity.ok(ApiResponse.success(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los productos.
     * @return 200 con la lista de productos.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerTodos() {
        return ResponseEntity.ok(ApiResponse.success(productoUseCaseIn.obtenerTodos()));
    }

    /**
     * Actualiza un producto existente.
     * @param id identificador del producto a actualizar.
     * @param request DTO con los datos a modificar.
     * @return 200 con el producto actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .build();
        return ResponseEntity.ok(ApiResponse.success(productoUseCaseIn.actualizar(id, producto)));
    }

    /**
     * Elimina un producto por su identificador.
     * @param id identificador del producto a eliminar.
     * @return 204 sin contenido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        productoUseCaseIn.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
