package com.bradleycorro.tataskilltest2.reportes.infrastructure.adapters.external;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad mínima para mapear la tabla cliente del primer microservicio.
 * Se utiliza @SecondaryTable para obtener el nombre desde la tabla persona,
 * dado que MS1 usa una estrategia de herencia JOINED.
 */
@Entity
@Table(name = "cliente")
@SecondaryTable(name = "persona", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@Getter
@Setter
public class ClienteExternalEntity {
    @Id
    private Long id;

    @Column(name = "clienteid", unique = true)
    private String clienteId;

    @Column(table = "persona", name = "nombre", nullable = false)
    private String nombre;
}
