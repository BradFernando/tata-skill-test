package com.bradleycorro.tataskillstest.clientes.domain.ports.out;

import com.bradleycorro.tataskillstest.clientes.domain.models.Cliente;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de Clientes.
 * Define el contrato que debe cumplir cualquier adaptador de persistencia.
 */
public interface IClienteRepositoryOut {
    /**
     * Guarda o actualiza un cliente.
     */
    Cliente save(Cliente cliente);

    /**
     * Busca un cliente por su identificador unico de base de datos.
     */
    Optional<Cliente> findById(Long id);

    /**
     * Recupera todos los clientes registrados.
     */
    List<Cliente> findAll();

    /**
     * Elimina un cliente por su ID.
     */
    void deleteById(Long id);
}
