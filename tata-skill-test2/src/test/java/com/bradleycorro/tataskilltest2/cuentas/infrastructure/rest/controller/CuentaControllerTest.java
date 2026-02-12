package com.bradleycorro.tataskilltest2.cuentas.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.cuentas.application.dto.CuentaRequest;
import com.bradleycorro.tataskilltest2.cuentas.domain.models.Cuenta;
import com.bradleycorro.tataskilltest2.cuentas.domain.ports.in.ICuentaUseCaseIn;
import com.bradleycorro.tataskilltest2.cuentas.infrastructure.adapters.mappers.CuentaApiMapper;
import com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CuentaController.class)
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICuentaUseCaseIn cuentaUseCaseIn;

    @MockBean
    private CuentaApiMapper cuentaApiMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCuenta() throws Exception {
        CuentaRequest request = new CuentaRequest();
        request.setNumeroCuenta("123456");
        request.setTipoCuenta("Ahorro");
        request.setSaldoInicial(100.0);
        request.setEstado(true);
        request.setClienteId(1L);

        Cuenta cuentaMock = new Cuenta(1L, "123456", "Ahorro", 100.0, true, 1L);
        Mockito.when(cuentaUseCaseIn.createCuenta(any())).thenReturn(cuentaMock);
        Mockito.when(cuentaApiMapper.fromRequest(any())).thenReturn(cuentaMock);
        Mockito.when(cuentaApiMapper.toResponse(any())).thenCallRealMethod();

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }
}
