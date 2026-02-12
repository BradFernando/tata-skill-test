package com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.repository.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cuentas")
public class CuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;

    @Column(nullable = false)
    private Boolean estado;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;
}
