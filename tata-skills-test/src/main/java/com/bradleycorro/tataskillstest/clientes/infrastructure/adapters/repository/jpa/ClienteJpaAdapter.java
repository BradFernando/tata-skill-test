package com.bradleycorro.tataskillstest.clientes.infrastructure.adapters.repository.jpa;

import com.bradleycorro.tataskillstest.clientes.domain.models.Cliente;
import com.bradleycorro.tataskillstest.clientes.domain.ports.out.IClienteRepositoryOut;
import com.bradleycorro.tataskillstest.clientes.infrastructure.adapters.mappers.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador de salida para la persistencia de Clientes usando Spring Data JPA.
 * Implementa la interfaz definida en el dominio (puerto) para aislar la infraestructura.
 */
@Component
@RequiredArgsConstructor
public class ClienteJpaAdapter implements IClienteRepositoryOut {

    private final IClienteJpaRepository clienteJpaRepository;
    private final ClienteMapper clienteMapper;

    /**
     * Persiste un cliente en la base de datos tras realizar el mapeo de dominio a entidad.
     */
    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = clienteMapper.toEntity(cliente);
        ClienteEntity savedEntity = clienteJpaRepository.save(entity);
        return clienteMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteJpaRepository.findById(id)
                .map(clienteMapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteJpaRepository.findAll().stream()
                .map(clienteMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        clienteJpaRepository.deleteById(id);
    }
}
