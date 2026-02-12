package com.bradleycorro.tataskillstest.clientes.domain.exceptions;

import com.bradleycorro.tataskillstest.shared.exception.BaseDomainException;
import org.springframework.http.HttpStatus;

public class ClienteNotFoundException extends BaseDomainException {
    public ClienteNotFoundException(String message) {
        super(message, "CLIENTE_NOT_FOUND", HttpStatus.NOT_FOUND.value());
    }
}
