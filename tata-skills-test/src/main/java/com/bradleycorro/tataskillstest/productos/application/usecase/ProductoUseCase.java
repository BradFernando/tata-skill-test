package com.bradleycorro.tataskillstest.productos.application.usecase;

import com.bradleycorro.tataskillstest.productos.domain.exceptions.ProductoNotFoundException;
import com.bradleycorro.tataskillstest.productos.domain.models.Producto;
import com.bradleycorro.tataskillstest.productos.domain.ports.in.IProductoUseCaseIn;
import com.bradleycorro.tataskillstest.productos.domain.ports.out.IProductoRepositoryOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de los casos de uso de Productos.
 * Orquesta la lógica de negocio y coordina el flujo de datos entre los puertos de entrada y salida.
 */
@Service
@RequiredArgsConstructor
public class ProductoUseCase implements IProductoUseCaseIn {

    private final IProductoRepositoryOut productoRepositoryOut;

    @Override
    public Producto crear(Producto producto) {
        // En un entorno real aquí se podrían aplicar reglas de negocio antes de guardar
        return productoRepositoryOut.save(producto);
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepositoryOut.findById(id);
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepositoryOut.findAll();
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        // Se valida la existencia del producto antes de proceder con la actualización
        return productoRepositoryOut.findById(id)
                .map(existingProducto -> {
                    existingProducto.setNombre(producto.getNombre());
                    existingProducto.setDescripcion(producto.getDescripcion());
                    existingProducto.setPrecio(producto.getPrecio());
                    existingProducto.setStock(producto.getStock());
                    return productoRepositoryOut.save(existingProducto);
                })
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @Override
    public void eliminar(Long id) {
        if (productoRepositoryOut.findById(id).isEmpty()) {
            throw new ProductoNotFoundException(id);
        }
        productoRepositoryOut.deleteById(id);
    }
}
