package com.bradleycorro.tataskillstest.productos.domain.ports.out;

import com.bradleycorro.tataskillstest.productos.domain.models.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoRepositoryOut {
    Producto save(Producto producto);
    Optional<Producto> findById(Long id);
    List<Producto> findAll();
    void deleteById(Long id);
}
