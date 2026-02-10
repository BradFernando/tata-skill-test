package com.bradleycorro.tataskillstest.shared.exception;

import lombok.Getter;

@Getter
public abstract class BaseDomainException extends RuntimeException {
    private final String type;
    private final int status;

    protected BaseDomainException(String message, String type, int status) {
        super(message);
        this.type = type;
        this.status = status;
    }
}
