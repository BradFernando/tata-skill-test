package com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.mappers;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.repository.jpa.CuentaEntity;

import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {
    public Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) return null;
        return new Cuenta(
                entity.getId(),
                entity.getNumeroCuenta(),
                entity.getTipoCuenta(),
                entity.getSaldoInicial(),
                entity.getEstado(),
                entity.getClienteId()
        );
    }

    public CuentaEntity toEntity(Cuenta domain) {
        if (domain == null) return null;
        CuentaEntity entity = new CuentaEntity();
        entity.setId(domain.getId());
        entity.setNumeroCuenta(domain.getNumeroCuenta());
        entity.setTipoCuenta(domain.getTipoCuenta());
        entity.setSaldoInicial(domain.getSaldoInicial());
        entity.setEstado(domain.getEstado());
        entity.setClienteId(domain.getClienteId());
        return entity;
    }
}
