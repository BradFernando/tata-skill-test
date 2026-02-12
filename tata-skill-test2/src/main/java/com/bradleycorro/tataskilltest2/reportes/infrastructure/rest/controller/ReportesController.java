package com.bradleycorro.tataskilltest2.reportes.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.out.ICuentaRepositoryOut;
import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import com.bradleycorro.tataskilltest2.movimientos.domain.ports.out.IMovimientoRepositoryOut;
import com.bradleycorro.tataskilltest2.reportes.application.dto.ReporteMovimientoDTO;
import com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Reportes", description = "Reportes de estado de cuenta")
@RestController
@RequestMapping("/reportes")
public class ReportesController {

    private final ICuentaRepositoryOut cuentaRepository;
    private final IMovimientoRepositoryOut movimientoRepository;

    public ReportesController(ICuentaRepositoryOut cuentaRepository, IMovimientoRepositoryOut movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Operation(summary = "Reporte de estado de cuenta por rango de fechas y cliente")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReporteMovimientoDTO>>> reporte(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<Movimiento> movimientos = movimientoRepository.findByClienteAndFechaRange(clienteId, startDate, endDate);

        List<ReporteMovimientoDTO> resultado = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            double saldoInicial = cuenta.getSaldoInicial();
            for (Movimiento m : movimientos) {
                if (!cuenta.getId().equals(m.getCuentaId())) continue;
                ReporteMovimientoDTO dto = new ReporteMovimientoDTO();
                dto.setFecha(m.getFecha());
                dto.setNumeroCuenta(cuenta.getNumeroCuenta());
                dto.setTipo(cuenta.getTipoCuenta());
                dto.setSaldoInicial(saldoInicial);
                dto.setEstado(cuenta.getEstado());
                dto.setMovimiento(m.getValor());
                dto.setSaldoDisponible(m.getSaldo());
                resultado.add(dto);
            }
        }

        return ResponseEntity.ok(ApiResponse.ok(resultado, "OK", "/reportes"));
    }
}
