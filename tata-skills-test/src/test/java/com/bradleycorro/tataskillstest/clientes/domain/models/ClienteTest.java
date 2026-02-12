package com.bradleycorro.tataskillstest.clientes.domain.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testClienteCreation() {
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nombre("Jose Lema")
                .genero("Masculino")
                .edad(30)
                .identificacion("1234567890")
                .direccion("Otavalo sn y principal")
                .telefono("098254785")
                .clienteId("CL123")
                .password("1234")
                .estado(true)
                .build();

        assertEquals(1L, cliente.getId());
        assertEquals("Jose Lema", cliente.getNombre());
        assertEquals("CL123", cliente.getClienteId());
        assertTrue(cliente.getEstado());
    }

    @Test
    void testClienteInheritance() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Marianela Montalvo");
        cliente.setClienteId("CL456");

        assertTrue(cliente instanceof Persona);
        assertEquals("Marianela Montalvo", cliente.getNombre());
        assertEquals("CL456", cliente.getClienteId());
    }
}
