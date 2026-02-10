package com.bradleycorro.tataskillstest.shared.infrastructure.handler;

import com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api.ApiResponse;
import com.bradleycorro.tataskillstest.shared.dto.responsegeneral.api.ErrorResponse;
import com.bradleycorro.tataskillstest.shared.exception.BaseDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones.
 * Transforma excepciones de negocio y técnicas a un formato uniforme de respuesta.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones del dominio, preservando el código de estado y tipo configurados.
     */
    @ExceptionHandler(BaseDomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleBaseDomainException(BaseDomainException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .status(ex.getStatus())
                .type(ex.getType())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(ex.getStatus()).body(ApiResponse.error(error));
    }

    /**
     * Maneja errores no controlados devolviendo un 500 con un mensaje genérico.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .type("INTERNAL_SERVER_ERROR")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(error));
    }
}
