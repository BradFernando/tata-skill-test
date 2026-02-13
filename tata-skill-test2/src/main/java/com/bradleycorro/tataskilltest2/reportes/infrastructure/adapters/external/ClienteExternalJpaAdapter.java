package com.bradleycorro.tataskilltest2.reportes.infrastructure.adapters.external;

import com.bradleycorro.tataskilltest2.reportes.domain.ports.out.IClienteExternalRepositoryOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteExternalJpaAdapter implements IClienteExternalRepositoryOut {

    private final IPersonaExternalJpaRepository repository;

    @Override
    public Optional<String> findNombreByClienteId(Long clienteId) {
        return repository.findById(clienteId)
                .map(PersonaExternalEntity::getNombre);
    }
}
