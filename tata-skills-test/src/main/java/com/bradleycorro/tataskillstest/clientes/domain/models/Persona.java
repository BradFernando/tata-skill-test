package com.bradleycorro.tataskillstest.clientes.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Representa la entidad base de negocio para una persona.
 * Define atributos comunes compartidos por diferentes tipos de actores en el sistema.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private Long id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
