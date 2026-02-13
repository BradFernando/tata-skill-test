package com.bradleycorro.tataskilltest2.reportes.infrastructure.adapters.external;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad mínima para mapear la tabla persona del primer microservicio.
 * Esto permite obtener el nombre del cliente sin necesidad de una llamada REST,
 * asumiendo que comparten la misma base de datos o esquema.
 */
@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonaExternalEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String nombre;
}
