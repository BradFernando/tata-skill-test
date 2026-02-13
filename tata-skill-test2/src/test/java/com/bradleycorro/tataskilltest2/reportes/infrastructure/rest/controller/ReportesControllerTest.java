package com.bradleycorro.tataskilltest2.reportes.infrastructure.rest.controller;

import com.bradleycorro.tataskilltest2.reportes.application.dto.ReporteMovimientoDTO;
import com.bradleycorro.tataskilltest2.reportes.domain.ports.in.IReporteUseCaseIn;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportesController.class)
public class ReportesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IReporteUseCaseIn reporteUseCaseIn;

    @Test
    public void testGetReporte() throws Exception {
        ReporteMovimientoDTO dto = new ReporteMovimientoDTO();
        dto.setFecha(LocalDateTime.now());
        dto.setClienteNombre("Jose Lema");
        dto.setNumeroCuenta("478758");
        dto.setTipo("Ahorro");
        dto.setSaldoInicial(2000.0);
        dto.setEstado(true);
        dto.setMovimiento(-575.0);
        dto.setSaldoDisponible(1425.0);

        Mockito.when(reporteUseCaseIn.generarReporte(any(), any(), any())).thenReturn(List.of(dto));

        mockMvc.perform(get("/reportes")
                        .param("clienteId", "1")
                        .param("startDate", "2026-02-13T00:00:00")
                        .param("endDate", "2026-02-13T23:59:59")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].clienteNombre").value("Jose Lema"));
    }
}
