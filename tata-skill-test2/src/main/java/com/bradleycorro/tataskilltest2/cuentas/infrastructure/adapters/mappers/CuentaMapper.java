package com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.mappers;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.repository.jpa.CuentaEntity;

import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {
    public Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) return null;
        return Cuenta.builder()
                .id(entity.getId())
                .numeroCuenta(entity.getNumeroCuenta())
                .tipoCuenta(entity.getTipoCuenta())
                .saldoInicial(entity.getSaldoInicial())
                .estado(entity.getEstado())
                .clienteId(entity.getClienteId())
                .build();
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
