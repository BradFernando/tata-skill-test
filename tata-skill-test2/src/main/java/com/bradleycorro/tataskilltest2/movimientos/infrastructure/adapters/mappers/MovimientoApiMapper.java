package com.bradleycorro.tataskilltest2.movimientos.infrastructure.adapters.mappers;

import com.bradleycorro.tataskilltest2.movimientos.application.dto.MovimientoRequest;
import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MovimientoApiMapper {
    public Movimiento fromRequest(MovimientoRequest r) {
        if (r == null) return null;
        return Movimiento.builder()
                .fecha(LocalDateTime.now())
                .tipoMovimiento(r.getTipoMovimiento())
                .valor(r.getValor())
                .cuentaId(r.getCuentaId())
                .build();
    }
}
