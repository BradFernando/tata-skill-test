package com.bradleycorro.tataskilltest2.cuentas.domain.ports.out;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import java.util.List;
import java.util.Optional;

public interface ICuentaRepositoryOut {
    Cuenta save(Cuenta cuenta);
    Optional<Cuenta> findById(Long id);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    List<Cuenta> findAll();
    List<Cuenta> findByClienteId(Long clienteId);
    boolean existsById(Long id);
    void deleteById(Long id);
}
