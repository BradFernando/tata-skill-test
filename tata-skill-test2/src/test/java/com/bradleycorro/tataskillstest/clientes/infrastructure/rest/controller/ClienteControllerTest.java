package com.bradleycorro.tataskillstest.clientes.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.clientes.application.dto.ClienteRequest;
import com.bradleycorro.tataskilltest2.clientes.domain.models.Cliente;
import com.bradleycorro.tataskilltest2.clientes.domain.ports.in.IClienteUseCaseIn;
import com.bradleycorro.tataskilltest2.shared.handler.GlobalExceptionHandler;
import com.bradleycorro.tataskilltest2.clientes.infrastructure.rest.controller.ClienteController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bradleycorro.tataskilltest2.clientes.infrastructure.adapters.mappers.ClienteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClienteControllerTest {

    private MockMvc mockMvc;
    private IClienteUseCaseIn useCase;
    private ObjectMapper om;

    @BeforeEach
    void setUp() {
        useCase = Mockito.mock(IClienteUseCaseIn.class);
        ClienteController controller = new ClienteController(useCase, new ClienteMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        om = new ObjectMapper();
    }

    @Test
    void post_creaCliente_retorna201() throws Exception {
        when(useCase.create(any())).thenReturn(new Cliente(1L, "Juan", "juan@example.com", "ACTIVO", OffsetDateTime.now()));
        ClienteRequest req = new ClienteRequest();
        req.setNombre("Juan");
        req.setEmail("juan@example.com");
        req.setEstado("ACTIVO");

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                //.andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void get_byId_200_y_404_mapeado() throws Exception {
        when(useCase.findById(1L)).thenReturn(Optional.of(new Cliente(1L, "A", "a@b.com", "ACTIVO", OffsetDateTime.now())));
        when(useCase.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1));

        mockMvc.perform(get("/api/v1/clientes/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
                //.andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void get_list_200() throws Exception {
        when(useCase.findAll()).thenReturn(List.of(new Cliente(1L, "A", "a@b.com", "ACTIVO", OffsetDateTime.now())));
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1));
    }

    @Test
    void delete_204() throws Exception {
        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
