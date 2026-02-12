package com.bradleycorro.tataskillstest.clientes.infrastructure.rest.controller;

import com.bradleycorro.tataskillstest.clientes.application.dto.ClienteRequest;
import com.bradleycorro.tataskillstest.clientes.application.dto.ClienteResponse;
import com.bradleycorro.tataskillstest.clientes.domain.models.Cliente;
import com.bradleycorro.tataskillstest.clientes.domain.ports.in.IClienteUseCaseIn;
import com.bradleycorro.tataskillstest.clientes.infrastructure.adapters.mappers.ClienteMapper;
import com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteUseCaseIn clienteUseCaseIn;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponse>> createCliente(@RequestBody ClienteRequest request) {
        Cliente cliente = clienteMapper.toDomain(request);
        Cliente createdCliente = clienteUseCaseIn.createCliente(cliente);
        ClienteResponse response = clienteMapper.toResponse(createdCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> updateCliente(@PathVariable Long id, @RequestBody ClienteRequest request) {
        Cliente cliente = clienteMapper.toDomain(request);
        Cliente updatedCliente = clienteUseCaseIn.updateCliente(id, cliente);
        ClienteResponse response = clienteMapper.toResponse(updatedCliente);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCliente(@PathVariable Long id) {
        clienteUseCaseIn.deleteCliente(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteUseCaseIn.getClienteById(id);
        ClienteResponse response = clienteMapper.toResponse(cliente);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteResponse>>> getAllClientes() {
        List<Cliente> clientes = clienteUseCaseIn.getAllClientes();
        List<ClienteResponse> response = clienteMapper.toResponseList(clientes);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
