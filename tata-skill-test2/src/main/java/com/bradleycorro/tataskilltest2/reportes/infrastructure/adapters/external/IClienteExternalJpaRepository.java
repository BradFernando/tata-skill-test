package com.bradleycorro.tataskilltest2.reportes.infrastructure.adapters.external;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteExternalJpaRepository extends JpaRepository<ClienteExternalEntity, Long> {
}
