package com.bradleycorro.tataskilltest2.movimientos.application.usecase;

import com.bradleycorro.tataskilltest2.cuentas.domain.exceptions.CuentaNotFoundException;
import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.out.ICuentaRepositoryOut;
import com.bradleycorro.tataskilltest2.movimientos.domain.exceptions.MovimientoNotFoundException;
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

    /**
     * Registra un nuevo movimiento en una cuenta.
     * Realiza validacion de saldo disponible antes de persistir.
     * @param movimiento Datos del movimiento (tipo, valor, cuenta).
     * @return Movimiento registrado con saldo actualizado.
     * @throws SaldoNoDisponibleException Si el saldo es insuficiente para retiros.
     * @throws CuentaNotFoundException Si la cuenta asociada no existe.
     */
    @Override
    @Transactional
    public Movimiento createMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
                .orElseThrow(CuentaNotFoundException::new);

        // Normalizar tipo de movimiento y ajustar signo del valor
        String tipo = movimiento.getTipoMovimiento().toLowerCase();
        double valorAjustado = movimiento.getValor();

        if (tipo.contains("retiro")) {
            valorAjustado = -Math.abs(valorAjustado);
        } else if (tipo.contains("deposito")) {
            valorAjustado = Math.abs(valorAjustado);
        }
        
        movimiento.setValor(valorAjustado);

        // Obtener el ultimo saldo disponible.
        // Si no hay movimientos, el saldo actual es el saldo inicial de la cuenta.
        List<Movimiento> ultimosMovimientos = repository.findByCuentaId(cuenta.getId());
        double saldoActual = ultimosMovimientos.isEmpty() ? cuenta.getSaldoInicial() : ultimosMovimientos.getFirst().getSaldo();

        // Calcular nuevo saldo considerando el valor del movimiento (positivo o negativo)
        double nuevoSaldo = saldoActual + movimiento.getValor();

        // Validacion de integridad financiera: el saldo no puede ser negativo
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
        // En una implementacion real, actualizar un movimiento podria requerir recalcular
        // los saldos de todos los movimientos posteriores. Por simplicidad del ejercicio
        // y dado que F2 dice "al registrar un movimiento", nos enfocaremos en la creacion.
        throw new UnsupportedOperationException("La actualizacion de movimientos no esta implementada por complejidad de recalculo de saldos");
    }

    @Override
    public void deleteMovimiento(Long id) {
        repository.findById(id).orElseThrow(MovimientoNotFoundException::new);
        repository.deleteById(id);
    }
}
