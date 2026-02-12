package com.bradleycorro.tataskilltest2.cuentas.application.usecase;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.in.ICuentaUseCaseIn;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.out.ICuentaRepositoryOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaUseCase implements ICuentaUseCaseIn {

    private final ICuentaRepositoryOut repository;

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        return repository.save(cuenta);
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return repository.findByNumeroCuenta(numeroCuenta);
    }

    @Override
    public List<Cuenta> getAllCuentas() {
        return repository.findAll();
    }

    @Override
    public Cuenta updateCuenta(Long id, Cuenta cuenta) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        cuenta.setId(id);
        return repository.save(cuenta);
    }

    @Override
    public void deleteCuenta(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        repository.deleteById(id);
    }
}
