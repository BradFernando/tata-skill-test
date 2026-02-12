package com.bradleycorro.tataskilltest2.cuentas.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaResponse {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Long clienteId;
}
