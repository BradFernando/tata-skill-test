package com.bradleycorro.tataskillstest.clientes.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Entidad de negocio que representa a un Cliente.
 * Hereda atributos de Persona y agrega informacion especifica de acceso y estado.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {
    private String clienteId;
    private String password;
    private Boolean estado;
}
