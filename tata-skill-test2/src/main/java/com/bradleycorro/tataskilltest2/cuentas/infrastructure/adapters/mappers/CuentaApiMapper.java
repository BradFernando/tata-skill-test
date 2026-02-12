package com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.mappers;

import com.bradleycorro.tataskilltest2.cuentas.application.dto.CuentaRequest;
import com.bradleycorro.tataskilltest2.cuentas.application.dto.CuentaResponse;
import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;

import org.springframework.stereotype.Component;

@Component
public class CuentaApiMapper {
    public Cuenta fromRequest(CuentaRequest r) {
        if (r == null) return null;
        return new Cuenta(null, r.getNumeroCuenta(), r.getTipoCuenta(), r.getSaldoInicial(), r.getEstado(), r.getClienteId());
    }

    public CuentaResponse toResponse(Cuenta d) {
        if (d == null) return null;
        CuentaResponse r = new CuentaResponse();
        r.setId(d.getId());
        r.setNumeroCuenta(d.getNumeroCuenta());
        r.setTipoCuenta(d.getTipoCuenta());
        r.setSaldoInicial(d.getSaldoInicial());
        r.setEstado(d.getEstado());
        r.setClienteId(d.getClienteId());
        return r;
    }
}
