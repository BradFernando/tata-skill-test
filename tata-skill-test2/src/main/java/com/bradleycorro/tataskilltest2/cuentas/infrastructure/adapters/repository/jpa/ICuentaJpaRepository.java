package com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ICuentaJpaRepository extends JpaRepository<CuentaEntity, Long> {
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
    List<CuentaEntity> findByClienteId(Long clienteId);
}
