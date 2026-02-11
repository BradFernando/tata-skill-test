package com.bradleycorro.tataskilltest2.clientes.infrastructure.adapters.mappers;

import com.bradleycorro.tataskilltest2.clientes.application.dto.ClienteRequest;
import com.bradleycorro.tataskilltest2.clientes.application.dto.ClienteResponse;
import com.bradleycorro.tataskilltest2.clientes.domain.models.Cliente;
import com.bradleycorro.tataskilltest2.clientes.infrastructure.adapters.repository.jpa.ClienteEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper dedicado para Cliente: Domain <-> Entity y Domain -> Response.
 */
@Component
public class ClienteMapper {

    public Cliente toDomain(ClienteEntity e) {
        if (e == null) return null;
        return new Cliente(e.getId(), e.getNombre(), e.getEmail(), e.getEstado(), e.getCreadoEn());
    }

    public ClienteEntity toEntity(Cliente d) {
        if (d == null) return null;
        ClienteEntity e = new ClienteEntity();
        e.setId(d.getId());
        e.setNombre(d.getNombre());
        e.setEmail(d.getEmail());
        e.setEstado(d.getEstado());
        e.setCreadoEn(d.getCreadoEn());
        return e;
    }

    public Cliente fromRequest(ClienteRequest r) {
        return new Cliente(null, r.getNombre(), r.getEmail(), r.getEstado(), null);
    }

    public ClienteResponse toResponse(Cliente d) {
        if (d == null) return null;
        ClienteResponse r = new ClienteResponse();
        r.setId(d.getId());
        r.setNombre(d.getNombre());
        r.setEmail(d.getEmail());
        r.setEstado(d.getEstado());
        r.setCreadoEn(d.getCreadoEn());
        return r;
    }
}
