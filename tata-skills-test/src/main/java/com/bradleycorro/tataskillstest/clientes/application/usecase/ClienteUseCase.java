package com.bradleycorro.tataskillstest.clientes.application.usecase;

import com.bradleycorro.tataskillstest.clientes.domain.exceptions.ClienteNotFoundException;
import com.bradleycorro.tataskillstest.clientes.domain.models.Cliente;
import com.bradleycorro.tataskillstest.clientes.domain.ports.in.IClienteUseCaseIn;
import com.bradleycorro.tataskillstest.clientes.domain.ports.out.IClienteRepositoryOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementacion del puerto de entrada (Caso de Uso) para la gestion de clientes.
 * Coordina la logica de negocio y la comunicacion con los adaptadores de salida.
 */
@Service
@RequiredArgsConstructor
public class ClienteUseCase implements IClienteUseCaseIn {

    private final IClienteRepositoryOut clienteRepositoryOut;

    /**
     * Registra un nuevo cliente asegurando la generacion de un identificador unico de sistema.
     */
    @Override
    public Cliente createCliente(Cliente cliente) {
        // Generar un clienteId unico si no se proporciona
        if (cliente.getClienteId() == null || cliente.getClienteId().isEmpty()) {
            cliente.setClienteId(UUID.randomUUID().toString());
        }
        return clienteRepositoryOut.save(cliente);
    }

    /**
     * Actualiza los datos de un cliente existente tras verificar su existencia.
     */
    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        return clienteRepositoryOut.findById(id)
                .map(existingCliente -> {
                    existingCliente.setNombre(cliente.getNombre());
                    existingCliente.setGenero(cliente.getGenero());
                    existingCliente.setEdad(cliente.getEdad());
                    existingCliente.setIdentificacion(cliente.getIdentificacion());
                    existingCliente.setDireccion(cliente.getDireccion());
                    existingCliente.setTelefono(cliente.getTelefono());
                    existingCliente.setPassword(cliente.getPassword());
                    existingCliente.setEstado(cliente.getEstado());
                    return clienteRepositoryOut.save(existingCliente);
                })
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public void deleteCliente(Long id) {
        if (clienteRepositoryOut.findById(id).isPresent()) {
            clienteRepositoryOut.deleteById(id);
        } else {
            throw new ClienteNotFoundException("Cliente no encontrado con id: " + id);
        }
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepositoryOut.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepositoryOut.findAll();
    }
}
