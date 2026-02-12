package com.bradleycorro.tataskilltest2.movimientos.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.movimientos.domain.exceptions.MovimientoNotFoundException;
import com.bradleycorro.tataskilltest2.movimientos.infrastructure.adapters.mappers.MovimientoApiMapper;
import com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api.ApiResponse;
import com.bradleycorro.tataskilltest2.movimientos.application.dto.MovimientoRequest;
import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import com.bradleycorro.tataskilltest2.movimientos.domain.ports.in.IMovimientoUseCaseIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movimientos", description = "Endpoints para gestión de movimientos")
@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final IMovimientoUseCaseIn movimientoUseCaseIn;
    private final MovimientoApiMapper movimientoApiMapper;

    @Operation(summary = "Registrar movimiento (depósito/retiro)")
    @PostMapping
    public ResponseEntity<ApiResponse<Movimiento>> create(@Valid @RequestBody MovimientoRequest request) {
        var created = movimientoUseCaseIn.createMovimiento(movimientoApiMapper.fromRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(created));
    }

    @Operation(summary = "Obtener movimiento por id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Movimiento>> findById(@PathVariable Long id) {
        var m = movimientoUseCaseIn.findById(id).orElseThrow(MovimientoNotFoundException::new);
        return ResponseEntity.ok(ApiResponse.success(m));
    }

    @Operation(summary = "Listar movimientos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Movimiento>>> findAll() {
        var list = movimientoUseCaseIn.getAllMovimientos();
        return ResponseEntity.ok(ApiResponse.success(list));
    }

    @Operation(summary = "Actualizar movimiento")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Movimiento>> update(@PathVariable Long id, @Valid @RequestBody MovimientoRequest request) {
        var updated = movimientoUseCaseIn.updateMovimiento(id, movimientoApiMapper.fromRequest(request));
        return ResponseEntity.ok(ApiResponse.success(updated));
    }

    @Operation(summary = "Eliminar movimiento")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        movimientoUseCaseIn.deleteMovimiento(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
