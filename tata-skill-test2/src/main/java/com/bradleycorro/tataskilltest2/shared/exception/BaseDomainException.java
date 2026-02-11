package com.bradleycorro.tataskilltest2.shared.exception;

import lombok.Getter;

/**
 * Excepción base para el dominio. Todas las excepciones de negocio deben extender esta clase.
 */
@Getter
public abstract class BaseDomainException extends RuntimeException {

    private final String code;

    protected BaseDomainException(String code, String message) {
        super(message);
        this.code = code;
    }

}
