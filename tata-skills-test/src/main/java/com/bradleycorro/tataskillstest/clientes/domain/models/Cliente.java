package com.bradleycorro.tataskillstest.clientes.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
