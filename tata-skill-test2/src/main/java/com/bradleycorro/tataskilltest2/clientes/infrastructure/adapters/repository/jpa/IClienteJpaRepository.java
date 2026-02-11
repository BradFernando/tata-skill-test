package com.bradleycorro.tataskilltest2.clientes.infrastructure.adapters.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
}
