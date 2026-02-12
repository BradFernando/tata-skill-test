package com.bradleycorro.tataskillstest.clientes.infrastructure.adapters.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByClienteId(String clienteId);
}
