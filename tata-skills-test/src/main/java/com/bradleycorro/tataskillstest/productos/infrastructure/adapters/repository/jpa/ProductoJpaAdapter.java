package com.bradleycorro.tataskillstest.productos.infrastructure.adapters.repository.jpa;

import com.bradleycorro.tataskillstest.productos.domain.models.Producto;
import com.bradleycorro.tataskillstest.productos.domain.ports.out.IProductoRepositoryOut;
import com.bradleycorro.tataskillstest.productos.infrastructure.adapters.mappers.ProductoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del puerto de salida del dominio usando Spring Data JPA.
 * Encapsula las operaciones de persistencia y mapea entidades a modelos de dominio.
 */
@Component
@RequiredArgsConstructor
public class ProductoJpaAdapter implements IProductoRepositoryOut {

    private final IProductoJpaRepository productoJpaRepository;
    private final ProductoMapper productoMapper;

    /**
     * Persiste un producto del dominio convirtiéndolo a entidad JPA y regresando el dominio almacenado.
     */
    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = productoMapper.toEntity(producto);
        ProductoEntity savedEntity = productoJpaRepository.save(entity);
        return productoMapper.toDomain(savedEntity);
    }

    /**
     * Busca un producto por su id en la base de datos.
     */
    @Override
    public Optional<Producto> findById(Long id) {
        return productoJpaRepository.findById(id)
                .map(productoMapper::toDomain);
    }

    /**
     * Obtiene todos los productos como una lista inmutable de dominio.
     */
    @Override
    public List<Producto> findAll() {
        return productoJpaRepository.findAll().stream()
                .map(productoMapper::toDomain)
                .toList();
    }

    /**
     * Elimina un producto por su id.
     */
    @Override
    public void deleteById(Long id) {
        productoJpaRepository.deleteById(id);
    }
}
