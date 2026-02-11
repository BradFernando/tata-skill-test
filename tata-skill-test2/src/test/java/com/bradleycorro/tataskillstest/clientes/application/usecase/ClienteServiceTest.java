package com.bradleycorro.tataskillstest.clientes.application.usecase;

import com.bradleycorro.tataskilltest2.clientes.application.usecase.IClienteServiceIn;
import com.bradleycorro.tataskilltest2.clientes.domain.exceptions.ClienteNotFoundException;
import com.bradleycorro.tataskilltest2.clientes.domain.models.Cliente;
import com.bradleycorro.tataskilltest2.clientes.domain.ports.out.IClienteRepositoryOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    private IClienteRepositoryOut repo;
    private IClienteServiceIn service;

    @BeforeEach
    void setUp() {
        repo = Mockito.mock(IClienteRepositoryOut.class);
        service = new IClienteServiceIn(repo);
    }

    @Test
    void crearCliente_happyPath() {
        Cliente toCreate = new Cliente(null, "Juan", "juan@example.com", "ACTIVO", null);
        when(repo.save(any())).thenAnswer(inv -> {
            Cliente c = inv.getArgument(0);
            c.setId(1L);
            return c;
        });
        Cliente created = service.create(toCreate);
        assertNotNull(created.getId());
        assertNotNull(created.getCreadoEn());
    }

    @Test
    void obtenerPorId_existente() {
        when(repo.findById(1L)).thenReturn(Optional.of(new Cliente(1L, "A", "a@b.com", "ACTIVO", OffsetDateTime.now())));
        Optional<Cliente> opt = service.findById(1L);
        assertTrue(opt.isPresent());
        assertEquals(1L, opt.get().getId());
    }

    @Test
    void actualizar_lanzaNotFound_siNoExiste() {
        when(repo.existsById(99L)).thenReturn(false);
        Cliente cliente = new Cliente(null, "A", "a@b.com", "ACTIVO", null);
        assertThrows(ClienteNotFoundException.class, () -> service.update(99L, cliente));
    }

    @Test
    void eliminar_lanzaNotFound_siNoExiste() {
        when(repo.existsById(99L)).thenReturn(false);
        assertThrows(ClienteNotFoundException.class, () -> service.delete(99L));
    }

    @Test
    void listar_retornaLista() {
        when(repo.findAll()).thenReturn(List.of(new Cliente(1L, "A", "a@b.com", "ACTIVO", OffsetDateTime.now())));
        List<Cliente> list = service.findAll();
        assertEquals(1, list.size());
    }
}
