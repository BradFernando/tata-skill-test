package com.bradleycorro.tataskilltest2.reportes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteMovimientoDTO {
    private LocalDateTime fecha;
    private String clienteNombre; // opcional si se provee por MS1; aquí no disponible
    private String numeroCuenta;
    private String tipo; // tipo de cuenta
    private Double saldoInicial;
    private Boolean estado;
    private Double movimiento; // valor del movimiento
    private Double saldoDisponible; // saldo luego del movimiento
}
