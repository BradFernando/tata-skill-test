package com.bradleycorro.tataskilltest2.clientes.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Modelo de dominio puro para Cliente.
 */
@Setter
@Getter
public class Cliente {
    private Long id;
    private String nombre;
    private String email;
    private String estado; // p.e. ACTIVO/INACTIVO
    private OffsetDateTime creadoEn;

    public Cliente(Long id, String nombre, String email, String estado, OffsetDateTime creadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
        this.creadoEn = creadoEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente other)) return false; // pattern matching Java 21
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
