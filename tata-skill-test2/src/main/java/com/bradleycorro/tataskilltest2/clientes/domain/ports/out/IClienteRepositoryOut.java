package com.bradleycorro.tataskilltest2.clientes.domain.ports.out;

import com.bradleycorro.tataskilltest2.clientes.domain.models.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para acceso a persistencia de Cliente.
 */
public interface IClienteRepositoryOut {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
    boolean existsById(Long id);
    void deleteById(Long id);
}
