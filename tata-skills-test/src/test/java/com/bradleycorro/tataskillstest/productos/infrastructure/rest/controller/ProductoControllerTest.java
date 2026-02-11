package com.bradleycorro.tataskillstest.productos.infrastructure.rest.controller;

import com.bradleycorro.tataskillstest.productos.application.dto.ProductoRequest;
import com.bradleycorro.tataskillstest.productos.domain.models.Producto;
import com.bradleycorro.tataskillstest.productos.domain.ports.in.IProductoUseCaseIn;
import com.bradleycorro.tataskillstest.productos.infrastructure.adapters.mappers.ProductoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.bradleycorro.tataskillstest.shared.handler.GlobalExceptionHandler;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IProductoUseCaseIn productoUseCaseIn;

    @InjectMocks
    private ProductoController productoController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Producto producto;
    private ProductoRequest request;

    @BeforeEach
    void setUp() {
        // Construimos el controlador con sus dependencias mockeadas
        productoController = new ProductoController(productoUseCaseIn, new ProductoMapper());
        // Creamos MockMvc en modo standalone sin contexto de Spring
        this.mockMvc = MockMvcBuilders.standaloneSetup(productoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        producto = Producto.builder()
                .id(1L)
                .nombre("Laptop")
                .precio(new BigDecimal("1500.00"))
                .stock(5)
                .build();

        request = ProductoRequest.builder()
                .nombre("Laptop")
                .precio(new BigDecimal("1500.00"))
                .stock(5)
                .build();
    }

    @Test
    void crear_DebeRetornar201() throws Exception {
        when(productoUseCaseIn.crear(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.nombre").value("Laptop"));
    }

    @Test
    void obtenerPorId_CuandoExiste_DebeRetornar200() throws Exception {
        when(productoUseCaseIn.obtenerPorId(1L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DebeRetornar404() throws Exception {
        when(productoUseCaseIn.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isNotFound());
    }
}
