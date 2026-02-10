package com.bradleycorro.tataskillstest.productos.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Representa la entidad de negocio de un Producto.
 * Esta clase es el núcleo del dominio y es independiente de cualquier framework de persistencia.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
}
