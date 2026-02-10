package com.bradleycorro.tataskillstest.productos.infrastructure.adapters.mappers;

import com.bradleycorro.tataskillstest.productos.domain.models.Producto;
import com.bradleycorro.tataskillstest.productos.infrastructure.adapters.repository.jpa.ProductoEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper responsable de convertir entre el modelo de dominio y la entidad JPA.
 * Mantiene el dominio aislado de detalles de persistencia.
 */
@Component
public class ProductoMapper {

    /**
     * Convierte una entidad JPA en un objeto de dominio.
     * @param entity entidad persistida.
     * @return objeto de dominio equivalente o null si la entidad es null.
     */
    public Producto toDomain(ProductoEntity entity) {
        if (entity == null) return null;
        return Producto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .stock(entity.getStock())
                .build();
    }

    /**
     * Convierte un objeto de dominio en entidad JPA.
     * @param domain objeto de dominio.
     * @return entidad lista para persistir o null si domain es null.
     */
    public ProductoEntity toEntity(Producto domain) {
        if (domain == null) return null;
        return ProductoEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .descripcion(domain.getDescripcion())
                .precio(domain.getPrecio())
                .stock(domain.getStock())
                .build();
    }
}
