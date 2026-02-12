package com.bradleycorro.tataskilltest2.movimientos.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.movimientos.application.dto.MovimientoRequest;
import com.bradleycorro.tataskilltest2.movimientos.domain.models.Movimiento;
import com.bradleycorro.tataskilltest2.movimientos.domain.ports.in.IMovimientoUseCaseIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovimientoController.class)
public class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMovimientoUseCaseIn useCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateMovimiento() throws Exception {
        MovimientoRequest request = new MovimientoRequest();
        request.setCuentaId(1L);
        request.setValor(100.0);
        request.setTipoMovimiento("Deposito");

        Movimiento movimientoMock = new Movimiento(1L, LocalDateTime.now(), "Deposito", 100.0, 200.0, 1L);
        Mockito.when(useCase.create(any(Movimiento.class))).thenReturn(movimientoMock);

        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.valor").value(100.0));
    }
}
