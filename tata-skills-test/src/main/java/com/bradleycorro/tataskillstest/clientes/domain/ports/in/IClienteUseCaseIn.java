package com.bradleycorro.tataskillstest.clientes.domain.ports.in;

import com.bradleycorro.tataskillstest.clientes.domain.models.Cliente;
import java.util.List;

public interface IClienteUseCaseIn {
    Cliente createCliente(Cliente cliente);
    Cliente updateCliente(Long id, Cliente cliente);
    void deleteCliente(Long id);
    Cliente getClienteById(Long id);
    List<Cliente> getAllClientes();
}
