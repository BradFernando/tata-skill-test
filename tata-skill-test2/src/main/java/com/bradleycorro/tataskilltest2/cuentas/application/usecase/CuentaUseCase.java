package com.bradleycorro.tataskilltest2.cuentas.application.usecase;

import com.bradleycorro.tataskilltest2.cuentas.domain.exceptions.CuentaNotFoundException;
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

    /**
     * Crea una nueva cuenta en el sistema.
     * @param cuenta Datos de la cuenta a persistir.
     * @return Cuenta creada con su ID generado.
     */
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

    /**
     * Actualiza una cuenta existente.
     * @param id Identificador unico de la cuenta.
     * @param cuenta Datos actualizados.
     * @return Cuenta persistida con los nuevos valores.
     * @throws CuentaNotFoundException Si la cuenta no existe.
     */
    @Override
    public Cuenta updateCuenta(Long id, Cuenta cuenta) {
        repository.findById(id).orElseThrow(CuentaNotFoundException::new);
        cuenta.setId(id);
        return repository.save(cuenta);
    }

    @Override
    public void deleteCuenta(Long id) {
        repository.findById(id).orElseThrow(CuentaNotFoundException::new);
        repository.deleteById(id);
    }
}
