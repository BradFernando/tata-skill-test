package com.bradleycorro.tataskilltest2.clientes.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * DTO público de salida para Cliente.
 */
@Setter
@Getter
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String email;
    private String estado;
    private OffsetDateTime creadoEn;

}
