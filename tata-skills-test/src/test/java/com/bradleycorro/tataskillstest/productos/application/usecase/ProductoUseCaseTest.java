package com.bradleycorro.tataskillstest.productos.application.usecase;

import com.bradleycorro.tataskillstest.productos.domain.exceptions.ProductoNotFoundException;
import com.bradleycorro.tataskillstest.productos.domain.models.Producto;
import com.bradleycorro.tataskillstest.productos.domain.ports.out.IProductoRepositoryOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoUseCaseTest {

    @Mock
    private IProductoRepositoryOut productoRepositoryOut;

    @InjectMocks
    private ProductoUseCase productoUseCase;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = Producto.builder()
                .id(1L)
                .nombre("Producto Test")
                .descripcion("Descripcion Test")
                .precio(new BigDecimal("100.00"))
                .stock(10)
                .build();
    }

    @Test
    void crear_DebeRetornarProductoGuardado() {
        when(productoRepositoryOut.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoUseCase.crear(producto);

        assertNotNull(resultado);
        assertEquals(producto.getNombre(), resultado.getNombre());
        verify(productoRepositoryOut, times(1)).save(producto);
    }

    @Test
    void obtenerPorId_CuandoExiste_DebeRetornarProducto() {
        when(productoRepositoryOut.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoUseCase.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void actualizar_CuandoNoExiste_DebeLanzarExcepcion() {
        when(productoRepositoryOut.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductoNotFoundException.class, () -> 
            productoUseCase.actualizar(1L, producto)
        );
    }
}
