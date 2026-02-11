package com.bradleycorro.tataskilltest2.clientes.application.usecase;

import com.bradleycorro.tataskilltest2.clientes.domain.exceptions.ClienteNotFoundException;
import com.bradleycorro.tataskilltest2.clientes.domain.models.Cliente;
import com.bradleycorro.tataskilltest2.clientes.domain.ports.in.IClienteUseCaseIn;
import com.bradleycorro.tataskilltest2.clientes.domain.ports.out.IClienteRepositoryOut;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de casos de uso para Cliente.
 */
@Service
public class IClienteServiceIn implements IClienteUseCaseIn {

    private final IClienteRepositoryOut repository;

    public IClienteServiceIn(IClienteRepositoryOut repository) {
        this.repository = repository;
    }

    @Override
    public Cliente create(Cliente cliente) {
        // regla simple: si no trae creadoEn, lo seteamos ahora
        if (cliente.getCreadoEn() == null) {
            cliente.setCreadoEn(OffsetDateTime.now());
        }
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Cliente update(Long id, Cliente datosActualizados) {
        if (!repository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        datosActualizados.setId(id);
        return repository.save(datosActualizados);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
