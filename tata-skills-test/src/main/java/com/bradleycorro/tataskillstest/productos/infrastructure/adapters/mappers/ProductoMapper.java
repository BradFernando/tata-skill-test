package com.bradleycorro.tataskillstest.productos.infrastructure.adapters.mappers;

import com.bradleycorro.tataskillstest.productos.application.dtos.ProductoResponse;
import com.bradleycorro.tataskillstest.productos.domain.models.Producto;
import com.bradleycorro.tataskillstest.productos.infrastructure.adapters.repository.jpa.ProductoEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper responsable de convertir entre el modelo de dominio, la entidad JPA y DTOs de salida.
 * Mantiene el dominio aislado de detalles de persistencia y de la capa externa.
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

    /**
     * Convierte un objeto de dominio a un DTO de respuesta.
     * @param domain objeto de dominio.
     * @return DTO de respuesta o null si domain es null.
     */
    public ProductoResponse toResponse(Producto domain) {
        if (domain == null) return null;
        return ProductoResponse.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .descripcion(domain.getDescripcion())
                .precio(domain.getPrecio())
                .stock(domain.getStock())
                .build();
    }
}
