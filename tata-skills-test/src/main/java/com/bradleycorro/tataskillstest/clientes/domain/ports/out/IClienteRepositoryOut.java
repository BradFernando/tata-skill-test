package com.bradleycorro.tataskillstest.clientes.domain.ports.out;

import com.bradleycorro.tataskillstest.clientes.domain.models.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteRepositoryOut {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);

    List<Cliente> findAll();
    void deleteById(Long id);
}
