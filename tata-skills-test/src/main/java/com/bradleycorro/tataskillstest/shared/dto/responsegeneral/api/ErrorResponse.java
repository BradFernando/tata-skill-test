package com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Estructura estandarizada para representar errores en la API.
 * Incluye código HTTP, tipo de error y mensaje legible para el cliente.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String type;
    private String message;
}
