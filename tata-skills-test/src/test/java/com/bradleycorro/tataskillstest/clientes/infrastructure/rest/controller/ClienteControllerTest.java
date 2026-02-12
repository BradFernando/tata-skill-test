package com.bradleycorro.tataskillstest.clientes.infrastructure.rest.controller;

import com.bradleycorro.tataskillstest.clientes.application.dto.ClienteRequest;
import com.bradleycorro.tataskillstest.clientes.domain.models.Cliente;
import com.bradleycorro.tataskillstest.clientes.domain.ports.in.IClienteUseCaseIn;
import com.bradleycorro.tataskillstest.clientes.infrastructure.adapters.mappers.ClienteMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IClienteUseCaseIn clienteUseCaseIn;

    @MockitoBean
    private ClienteMapper clienteMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCliente_ShouldReturnCreated() throws Exception {
        ClienteRequest request = ClienteRequest.builder()
                .nombre("Jose Lema")
                .identificacion("123456")
                .password("1234")
                .estado(true)
                .build();

        Cliente cliente = new Cliente();
        cliente.setNombre("Jose Lema");

        when(clienteMapper.toDomain(any(ClienteRequest.class))).thenReturn(cliente);
        when(clienteUseCaseIn.createCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void getAllClientes_ShouldReturnList() throws Exception {
        when(clienteUseCaseIn.getAllClientes()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
