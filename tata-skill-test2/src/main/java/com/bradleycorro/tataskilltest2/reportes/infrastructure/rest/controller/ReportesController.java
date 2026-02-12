package com.bradleycorro.tataskilltest2.reportes.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.reportes.application.dto.ReporteMovimientoDTO;
import com.bradleycorro.tataskilltest2.reportes.domain.ports.in.IReporteUseCaseIn;
import com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Reportes", description = "Reportes de estado de cuenta")
@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportesController {

    private final IReporteUseCaseIn reporteUseCaseIn;

    @Operation(summary = "Reporte de estado de cuenta por rango de fechas y cliente")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReporteMovimientoDTO>>> reporte(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<ReporteMovimientoDTO> resultado = reporteUseCaseIn.generarReporte(clienteId, startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(resultado));
    }
}
