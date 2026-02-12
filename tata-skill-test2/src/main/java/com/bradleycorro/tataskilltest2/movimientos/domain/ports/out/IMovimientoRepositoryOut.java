package com.bradleycorro.tataskilltest2.movimientos.domain.ports.out;

import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IMovimientoRepositoryOut {
    Movimiento save(Movimiento movimiento);
    Optional<Movimiento> findById(Long id);
    List<Movimiento> findAll();
    List<Movimiento> findByCuentaId(Long cuentaId);
    List<Movimiento> findByClienteAndFechaRange(Long clienteId, LocalDateTime start, LocalDateTime end);
    void deleteById(Long id);
}
