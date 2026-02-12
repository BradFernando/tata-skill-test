package com.bradleycorro.tataskillstest.clientes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String password;
    private Boolean estado;
}
