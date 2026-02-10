package com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contenedor estándar para todas las respuestas de la API.
 * Garantiza consistencia entre respuestas exitosas y de error en todo el microservicio.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ErrorResponse error;

    /**
     * Construye una respuesta exitosa.
     * @param data carga útil devuelta por el endpoint.
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    /**
     * Construye una respuesta de error con la estructura estandarizada.
     * @param error detalle del error.
     */
    public static <T> ApiResponse<T> error(ErrorResponse error) {
        return ApiResponse.<T>builder()
                .success(false)
                .error(error)
                .build();
    }
}
