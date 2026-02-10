package com.bradleycorro.tataskillstest.productos.domain.ports.in;

import com.bradleycorro.tataskillstest.productos.domain.models.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada que define las operaciones permitidas sobre el dominio de Productos.
 * Seguido por la arquitectura hexagonal para desacoplar la lógica de negocio de la infraestructura.
 */
public interface IProductoUseCaseIn {
    /**
     * Crea un nuevo producto en el sistema.
     * @param producto Objeto con los datos del producto a crear.
     * @return El producto creado con su identificador generado.
     */
    Producto crear(Producto producto);

    /**
     * Busca un producto por su identificador único.
     * @param id Identificador del producto.
     * @return Un Optional que contiene el producto si se encuentra, o vacío en caso contrario.
     */
    Optional<Producto> obtenerPorId(Long id);

    /**
     * Obtiene todos los productos registrados.
     * @return Lista de productos.
     */
    List<Producto> obtenerTodos();

    /**
     * Actualiza la información de un producto existente.
     * @param id Identificador del producto a actualizar.
     * @param producto Datos actualizados del producto.
     * @return El producto actualizado.
     */
    Producto actualizar(Long id, Producto producto);

    /**
     * Elimina un producto por su identificador.
     * @param id Identificador del producto a eliminar.
     */
    void eliminar(Long id);
}
