package com.bradleycorro.tataskilltest2.movimientos.infrastructure.adapters.repository.jpa;

import com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.repository.jpa.CuentaEntity;
import com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.repository.jpa.ICuentaJpaRepository;
import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import com.bradleycorro.tataskilltest2.movimientos.domain.ports.out.IMovimientoRepositoryOut;
import com.bradleycorro.tataskilltest2.movimientos.infrastructure.adapters.mappers.MovimientoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovimientoJpaAdapter implements IMovimientoRepositoryOut {

    private final IMovimientoJpaRepository repository;
    private final ICuentaJpaRepository cuentaJpaRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    public Movimiento save(Movimiento movimiento) {
        CuentaEntity cuenta = cuentaJpaRepository.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        MovimientoEntity entity = movimientoMapper.toEntity(movimiento, cuenta);
        return movimientoMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return repository.findById(id).map(movimientoMapper::toDomain);
    }

    @Override
    public List<Movimiento> findAll() {
        return repository.findAll().stream()
                .map(movimientoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movimiento> findByCuentaId(Long cuentaId) {
        return repository.findByCuentaIdOrderByFechaDesc(cuentaId).stream()
                .map(movimientoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movimiento> findByClienteAndFechaRange(Long clienteId, LocalDateTime start, LocalDateTime end) {
        return repository.findByClienteAndFechaRange(clienteId, start, end).stream()
                .map(movimientoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
