package com.bradleycorro.tataskilltest2.clientes.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.clientes.application.dto.ClienteRequest;
import com.bradleycorro.tataskilltest2.clientes.application.dto.ClienteResponse;
import com.bradleycorro.tataskilltest2.clientes.domain.exceptions.ClienteNotFoundException;
import com.bradleycorro.tataskilltest2.clientes.domain.ports.in.IClienteUseCaseIn;
import com.bradleycorro.tataskilltest2.clientes.infrastructure.adapters.mappers.ClienteMapper;
import com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST de Clientes.
 */
@Tag(name = "Clientes", description = "Endpoints para gestión de clientes")
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteUseCaseIn clienteUseCaseIn;
    private final ClienteMapper clienteMapper;

    @Operation(summary = "Crear cliente", description = "Crea un nuevo cliente")
    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponse>> create(@Valid @RequestBody ClienteRequest request) {
        var created = clienteUseCaseIn.create(clienteMapper.fromRequest(request));
        var response = clienteMapper.toResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @Operation(summary = "Obtener cliente por id", description = "Devuelve un cliente si existe")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> findById(@PathVariable Long id) {
        return clienteUseCaseIn.findById(id)
                .map(d -> ResponseEntity.ok(ApiResponse.success(clienteMapper.toResponse(d))))
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Operation(summary = "Listar clientes", description = "Retorna todos los clientes")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteResponse>>> findAll() {
        var list = clienteUseCaseIn.findAll().stream().map(clienteMapper::toResponse).toList();
        return ResponseEntity.ok(ApiResponse.success(list));
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza un cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> update(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        var updated = clienteUseCaseIn.update(id, clienteMapper.fromRequest(request));
        return ResponseEntity.ok(ApiResponse.success(clienteMapper.toResponse(updated)));
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        clienteUseCaseIn.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success(null));
    }
}
