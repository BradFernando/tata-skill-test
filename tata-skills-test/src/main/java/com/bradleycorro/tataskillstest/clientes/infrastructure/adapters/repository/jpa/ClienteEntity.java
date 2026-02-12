package com.bradleycorro.tataskillstest.clientes.infrastructure.adapters.repository.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entidad JPA para la tabla 'cliente'.
 * Representa la especializacion de Persona en la persistencia.
 */
@Entity
@Table(name = "cliente")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity extends PersonaEntity {
    @Column(name = "clienteid", unique = true, nullable = false)
    private String clienteId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean estado;
}
