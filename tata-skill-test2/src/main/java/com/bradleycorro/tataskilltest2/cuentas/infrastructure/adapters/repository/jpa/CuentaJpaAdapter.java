package com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.repository.jpa;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.out.ICuentaRepositoryOut;
import com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.mappers.CuentaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CuentaJpaAdapter implements ICuentaRepositoryOut {

    private final ICuentaJpaRepository repository;
    private final CuentaMapper cuentaMapper;

    @Override
    public Cuenta save(Cuenta cuenta) {
        CuentaEntity entity = cuentaMapper.toEntity(cuenta);
        return cuentaMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return repository.findById(id).map(cuentaMapper::toDomain);
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return repository.findByNumeroCuenta(numeroCuenta).map(cuentaMapper::toDomain);
    }

    @Override
    public List<Cuenta> findAll() {
        return repository.findAll().stream()
                .map(cuentaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cuenta> findByClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId).stream()
                .map(cuentaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
