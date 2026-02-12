package com.bradleycorro.tataskilltest2.movimientos.application.usecase;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.out.ICuentaRepositoryOut;
import com.bradleycorro.tataskilltest2.movimientos.domain.exceptions.SaldoNoDisponibleException;
import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import com.bradleycorro.tataskilltest2.movimientos.domain.ports.in.IMovimientoUseCaseIn;
import com.bradleycorro.tataskilltest2.movimientos.domain.ports.out.IMovimientoRepositoryOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovimientoUseCase implements IMovimientoUseCaseIn {

    private final IMovimientoRepositoryOut repository;
    private final ICuentaRepositoryOut cuentaRepository;

    @Override
    @Transactional
    public Movimiento createMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        // Obtener el último saldo disponible.
        // Si no hay movimientos, el saldo actual es el saldo inicial.
        List<Movimiento> últimosMovimientos = repository.findByCuentaId(cuenta.getId());
        double saldoActual = últimosMovimientos.isEmpty() ? cuenta.getSaldoInicial() : últimosMovimientos.get(0).getSaldo();

        double nuevoSaldo = saldoActual + movimiento.getValor();

        if (nuevoSaldo < 0) {
            throw new SaldoNoDisponibleException();
        }

        movimiento.setSaldo(nuevoSaldo);
        if (movimiento.getFecha() == null) {
            movimiento.setFecha(LocalDateTime.now());
        }

        return repository.save(movimiento);
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Movimiento> getAllMovimientos() {
        return repository.findAll();
    }

    @Override
    public Movimiento updateMovimiento(Long id, Movimiento movimiento) {
        // En una implementación real, actualizar un movimiento podría requerir re-calcular
        // los saldos de todos los movimientos posteriores. Por simplicidad del ejercicio
        // y dado que F2 dice "al registrar un movimiento", nos enfocaremos en la creación.
        throw new UnsupportedOperationException("La actualización de movimientos no está implementada por complejidad de recálculo de saldos");
    }

    @Override
    public void deleteMovimiento(Long id) {
        repository.deleteById(id);
    }
}
