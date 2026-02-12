package com.bradleycorro.tataskillstest.clientes.infrastructure.adapters.mappers;

import com.bradleycorro.tataskillstest.clientes.application.dto.ClienteRequest;
import com.bradleycorro.tataskillstest.clientes.application.dto.ClienteResponse;
import com.bradleycorro.tataskillstest.clientes.domain.models.Cliente;
import com.bradleycorro.tataskillstest.clientes.infrastructure.adapters.repository.jpa.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapeador especializado para la entidad Cliente.
 * Centraliza la conversion entre los modelos de Dominio, Entidades JPA y DTOs de API.
 */
@Component
public class ClienteMapper {

    /**
     * Convierte una entidad de base de datos en un objeto de dominio.
     */
    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) return null;
        return Cliente.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .genero(entity.getGenero())
                .edad(entity.getEdad())
                .identificacion(entity.getIdentificacion())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .clienteId(entity.getClienteId())
                .password(entity.getPassword())
                .estado(entity.getEstado())
                .build();
    }

    public ClienteEntity toEntity(Cliente domain) {
        if (domain == null) return null;
        return ClienteEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .genero(domain.getGenero())
                .edad(domain.getEdad())
                .identificacion(domain.getIdentificacion())
                .direccion(domain.getDireccion())
                .telefono(domain.getTelefono())
                .clienteId(domain.getClienteId())
                .password(domain.getPassword())
                .estado(domain.getEstado())
                .build();
    }

    public Cliente toDomain(ClienteRequest request) {
        if (request == null) return null;
        return Cliente.builder()
                .nombre(request.getNombre())
                .genero(request.getGenero())
                .edad(request.getEdad())
                .identificacion(request.getIdentificacion())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .password(request.getPassword())
                .estado(request.getEstado())
                .build();
    }

    public ClienteResponse toResponse(Cliente domain) {
        if (domain == null) return null;
        return ClienteResponse.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .direccion(domain.getDireccion())
                .telefono(domain.getTelefono())
                .password(domain.getPassword())
                .estado(domain.getEstado())
                .build();
    }

    public List<ClienteResponse> toResponseList(List<Cliente> domainList) {
        return domainList.stream()
                .map(this::toResponse)
                .toList();
    }
}
