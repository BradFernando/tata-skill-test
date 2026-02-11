package com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Estructura estandarizada para representar errores en la API.
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
