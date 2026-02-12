package com.bradleycorro.tataskilltest2.reportes.domain.ports.in;

import com.bradleycorro.tataskilltest2.reportes.application.dto.ReporteMovimientoDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface IReporteUseCaseIn {
    List<ReporteMovimientoDTO> generarReporte(Long clienteId, LocalDateTime startDate, LocalDateTime endDate);
}
