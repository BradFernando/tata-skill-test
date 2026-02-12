package com.bradleycorro.tataskilltest2.movimientos.domain.exceptions;

import com.bradleycorro.tataskilltest2.shared.exception.BaseDomainException;

public class SaldoNoDisponibleException extends BaseDomainException {
    public SaldoNoDisponibleException() {
        super("SALDO_NO_DISPONIBLE", "Saldo no disponible");
    }
}
