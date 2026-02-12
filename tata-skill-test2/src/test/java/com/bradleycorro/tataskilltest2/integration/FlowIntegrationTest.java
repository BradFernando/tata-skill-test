package com.bradleycorro.tataskilltest2.integration;

import com.bradleycorro.tataskilltest2.cuentas.application.dto.CuentaRequest;
import com.bradleycorro.tataskilltest2.movimientos.application.dto.MovimientoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFullFlow() throws Exception {
        // 1. Crear Cuenta
        CuentaRequest cuentaReq = new CuentaRequest();
        cuentaReq.setNumeroCuenta("225487");
        cuentaReq.setTipoCuenta("Corriente");
        cuentaReq.setSaldoInicial(100.0);
        cuentaReq.setEstado(true);
        cuentaReq.setClienteId(2L);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuentaReq)))
                .andExpect(status().isCreated());

        // 2. Registrar Movimiento (Depósito 600)
        MovimientoRequest movReq = new MovimientoRequest();
        movReq.setCuentaId(1L); // Asumiendo id 1
        movReq.setValor(600.0);
        movReq.setTipoMovimiento("Deposito");

        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movReq)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.saldo").value(700.0));

        // 3. Verificar Reporte
        mockMvc.perform(get("/reportes")
                        .param("clienteId", "2")
                        .param("startDate", "2020-01-01T00:00:00")
                        .param("endDate", "2030-01-01T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].saldoDisponible").value(700.0));
    }
}
