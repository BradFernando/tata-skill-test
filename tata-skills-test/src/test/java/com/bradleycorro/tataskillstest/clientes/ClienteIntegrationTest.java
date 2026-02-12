package com.bradleycorro.tataskillstest.clientes;

import com.bradleycorro.tataskillstest.clientes.application.dto.ClienteRequest;
import com.bradleycorro.tataskillstest.clientes.application.dto.ClienteResponse;
import com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ClienteIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String CLIENTES_URL = "/clientes";

    @Test
    void testCreateAndGetCliente() {
        // 1. Create Cliente
        ClienteRequest request = ClienteRequest.builder()
                .nombre("Juan Osorio")
                .genero("Masculino")
                .edad(35)
                .identificacion("UNIQUE_ID_" + System.currentTimeMillis())
                .direccion("13 junio y Equinoccial")
                .telefono("098874587")
                .password("5678")
                .estado(true)
                .build();

        ResponseEntity<ApiResponse<ClienteResponse>> postResponse = restTemplate.exchange(
                CLIENTES_URL,
                HttpMethod.POST,
                new org.springframework.http.HttpEntity<>(request),
                new ParameterizedTypeReference<ApiResponse<ClienteResponse>>() {}
        );

        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody());
        assertTrue(postResponse.getBody().isSuccess());
        ClienteResponse created = postResponse.getBody().getData();
        assertEquals("Juan Osorio", created.getNombre());
        assertNotNull(created.getId());

        // 2. Get Cliente by ID
        ResponseEntity<ApiResponse<ClienteResponse>> getResponse = restTemplate.exchange(
                CLIENTES_URL + "/" + created.getId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<ClienteResponse>>() {}
        );

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Juan Osorio", getResponse.getBody().getData().getNombre());
    }
}
