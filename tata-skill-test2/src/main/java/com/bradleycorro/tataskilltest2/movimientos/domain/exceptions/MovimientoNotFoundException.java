package com.bradleycorro.tataskilltest2.movimientos.domain.exceptions;

import com.bradleycorro.tataskilltest2.shared.exception.BaseDomainException;

/**
 * Excepcion lanzada cuando no se encuentra un movimiento.
 */
public class MovimientoNotFoundException extends BaseDomainException {

    public MovimientoNotFoundException() {
        super("MOVIMIENTO_NOT_FOUND", "Movimiento no encontrado");
    }

}
