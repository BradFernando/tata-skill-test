package com.bradleycorro.tataskilltest2.cuentas.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.cuentas.application.dto.CuentaRequest;
import com.bradleycorro.tataskilltest2.cuentas.application.dto.CuentaResponse;
import com.bradleycorro.tataskilltest2.cuentas.domain.exceptions.CuentaNotFoundException;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.in.ICuentaUseCaseIn;
import com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.mappers.CuentaApiMapper;
import com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cuentas", description = "Endpoints para gestión de cuentas")
@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final ICuentaUseCaseIn cuentaUseCaseIn;
    private final CuentaApiMapper cuentaApiMapper;

    @Operation(summary = "Crear cuenta")
    @PostMapping
    public ResponseEntity<ApiResponse<CuentaResponse>> create(@Valid @RequestBody CuentaRequest request) {
        var created = cuentaUseCaseIn.createCuenta(cuentaApiMapper.fromRequest(request));
        var response = cuentaApiMapper.toResponse(created);
        var body = ApiResponse.success(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @Operation(summary = "Obtener cuenta por id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CuentaResponse>> findById(@PathVariable Long id) {
        var c = cuentaUseCaseIn.findById(id).orElseThrow(CuentaNotFoundException::new);
        return ResponseEntity.ok(ApiResponse.success(cuentaApiMapper.toResponse(c)));
    }

    @Operation(summary = "Listar cuentas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CuentaResponse>>> findAll() {
        var list = cuentaUseCaseIn.getAllCuentas().stream().map(cuentaApiMapper::toResponse).toList();
        return ResponseEntity.ok(ApiResponse.success(list));
    }

    @Operation(summary = "Actualizar cuenta")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CuentaResponse>> update(@PathVariable Long id, @Valid @RequestBody CuentaRequest request) {
        var updated = cuentaUseCaseIn.updateCuenta(id, cuentaApiMapper.fromRequest(request));
        return ResponseEntity.ok(ApiResponse.success(cuentaApiMapper.toResponse(updated)));
    }

    @Operation(summary = "Eliminar cuenta")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        cuentaUseCaseIn.deleteCuenta(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
