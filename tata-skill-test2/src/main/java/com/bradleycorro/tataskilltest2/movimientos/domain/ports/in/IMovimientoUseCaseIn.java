package com.bradleycorro.tataskilltest2.movimientos.domain.ports.in;

import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import java.util.List;
import java.util.Optional;

public interface IMovimientoUseCaseIn {
    Movimiento createMovimiento(Movimiento movimiento);
    Optional<Movimiento> findById(Long id);
    List<Movimiento> getAllMovimientos();
    Movimiento updateMovimiento(Long id, Movimiento movimiento);
    void deleteMovimiento(Long id);
}
