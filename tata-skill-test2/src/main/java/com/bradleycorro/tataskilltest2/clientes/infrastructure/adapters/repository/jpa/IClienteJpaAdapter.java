package com.bradleycorro.tataskilltest2.clientes.infrastructure.adapters.repository.jpa;

import com.bradleycorro.tataskilltest2.clientes.domain.models.Cliente;
import com.bradleycorro.tataskilltest2.clientes.domain.ports.out.IClienteRepositoryOut;
import com.bradleycorro.tataskilltest2.clientes.infrastructure.adapters.mappers.ClienteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador JPA que implementa el puerto de salida del dominio.
 */
@Repository
public class IClienteJpaAdapter implements IClienteRepositoryOut {

    private final IClienteJpaRepository repository;
    private final ClienteMapper mapper;

    public IClienteJpaAdapter(IClienteJpaRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Cliente save(Cliente cliente) {
        var entity = mapper.toEntity(cliente);
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
