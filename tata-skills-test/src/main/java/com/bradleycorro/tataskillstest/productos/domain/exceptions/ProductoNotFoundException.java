package com.bradleycorro.tataskillstest.productos.domain.exceptions;

import com.bradleycorro.tataskillstest.shared.exception.BaseDomainException;

public class ProductoNotFoundException extends BaseDomainException {
    public ProductoNotFoundException(Long id) {
        super("Producto no encontrado con id: " + id, "NOT_FOUND", 404);
    }
}
