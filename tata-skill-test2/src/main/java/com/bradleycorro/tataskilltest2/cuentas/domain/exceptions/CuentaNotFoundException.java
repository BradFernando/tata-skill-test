package com.bradleycorro.tataskilltest2.cuentas.domain.exceptions;

import com.bradleycorro.tataskilltest2.shared.exception.BaseDomainException;

public class CuentaNotFoundException extends BaseDomainException {
    public CuentaNotFoundException() {
        super("CUENTA_NOT_FOUND", "Cuenta no encontrada");
    }

    public CuentaNotFoundException(String message) {
        super("CUENTA_NOT_FOUND", message);
    }
}
