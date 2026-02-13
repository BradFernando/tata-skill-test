package com.bradleycorro.tataskilltest2.reportes.domain.ports.out;

import java.util.Optional;

public interface IClienteExternalRepositoryOut {
    Optional<String> findNombreByClienteId(Long clienteId);
}
