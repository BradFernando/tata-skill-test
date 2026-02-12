package com.bradleycorro.tataskilltest2.movimientos.infrastructure.adapters.mappers;

import com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.repository.jpa.CuentaEntity;
import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import com.bradleycorro.tataskilltest2.movimientos.infrastructure.adapters.repository.jpa.MovimientoEntity;

import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {
    public Movimiento toDomain(MovimientoEntity entity) {
        if (entity == null) return null;
        return new Movimiento(
                entity.getId(),
                entity.getFecha(),
                entity.getTipoMovimiento(),
                entity.getValor(),
                entity.getSaldo(),
                entity.getCuenta() != null ? entity.getCuenta().getId() : null
        );
    }

    public MovimientoEntity toEntity(Movimiento domain, CuentaEntity cuenta) {
        if (domain == null) return null;
        MovimientoEntity entity = new MovimientoEntity();
        entity.setId(domain.getId());
        entity.setFecha(domain.getFecha());
        entity.setTipoMovimiento(domain.getTipoMovimiento());
        entity.setValor(domain.getValor());
        entity.setSaldo(domain.getSaldo());
        entity.setCuenta(cuenta);
        return entity;
    }
}
