package com.bradleycorro.tataskillstest.productos.infrastructure.adapters.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio Spring Data JPA para la entidad {@link ProductoEntity}.
 * Delegado por el adaptador de persistencia para operaciones CRUD.
 */
@Repository
public interface IProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {
}
