package com.bradleycorro.tataskilltest2.clientes.domain.exceptions;

import com.bradleycorro.tataskilltest2.shared.exception.BaseDomainException;

/**
 * Se lanza cuando un Cliente no existe para un id especificado.
 */
public class ClienteNotFoundException extends BaseDomainException {
    public ClienteNotFoundException(Long id) {
        super("CLIENTE_NOT_FOUND", "Cliente no encontrado con id=" + id);
    }
}
