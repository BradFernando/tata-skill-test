package com.bradleycorro.tataskilltest2.clientes.domain.ports.in;

import com.bradleycorro.tataskilltest2.clientes.domain.models.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada que define las operaciones de negocio sobre Cliente.
 */
public interface IClienteUseCaseIn {
    Cliente create(Cliente cliente);
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
    Cliente update(Long id, Cliente datosActualizados);
    void delete(Long id);
}
