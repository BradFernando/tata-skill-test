package com.bradleycorro.tataskilltest2.movimientos.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoRequest {
    @NotNull(message = "El ID de la cuenta es obligatorio")
    private Long cuentaId;

    @NotNull(message = "El valor del movimiento es obligatorio")
    private Double valor;

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private String tipoMovimiento;
}
