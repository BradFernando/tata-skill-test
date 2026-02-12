package com.bradleycorro.tataskilltest2.cuentas.domain.ports.in;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import java.util.List;
import java.util.Optional;

public interface ICuentaUseCaseIn {
    Cuenta createCuenta(Cuenta cuenta);
    Optional<Cuenta> findById(Long id);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    List<Cuenta> getAllCuentas();
    Cuenta updateCuenta(Long id, Cuenta cuenta);
    void deleteCuenta(Long id);
}
