package com.bradleycorro.tataskilltest2.shared.handler;

import com.bradleycorro.tataskilltest2.cuentas.domain.exceptions.CuentaNotFoundException;
import com.bradleycorro.tataskilltest2.movimientos.domain.exceptions.MovimientoNotFoundException;
import com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api.ApiResponse;
import com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api.ErrorResponse;
import com.bradleycorro.tataskilltest2.shared.exception.BaseDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler transforma todas las excepciones capturadas en la capa REST 
 * hacia un formato estandarizado de ApiResponse.error utilizando el ErrorResponse.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String NOT_FOUND_TYPE = "NOT_FOUND";

    /**
     * Captura excepciones de entidad no encontrada (404).
     */
    @ExceptionHandler(CuentaNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCuentaNotFound(CuentaNotFoundException ex) {
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .type(NOT_FOUND_TYPE)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(err));
    }

    @ExceptionHandler(MovimientoNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleMovimientoNotFound(MovimientoNotFoundException ex) {
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .type(NOT_FOUND_TYPE)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(err));
    }

    /**
     * Captura excepciones de negocio/dominio (422).
     */
    @ExceptionHandler(BaseDomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleDomain(BaseDomainException ex) {
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .type(ex.getCode() != null ? ex.getCode() : "DOMAIN_ERROR")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ApiResponse.error(err));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        BindingResult binding = ex.getBindingResult();
        String message = binding.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type("BAD_REQUEST")
                .message("Error de validacion: " + message)
                .build();
        return ResponseEntity.badRequest().body(ApiResponse.error(err));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraint(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .type("CONSTRAINT_VIOLATION")
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ApiResponse.error(err));
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ApiResponse<Void>> handleNotFound(RuntimeException ex) {
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .type(NOT_FOUND_TYPE)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(err));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .type("INTERNAL_SERVER_ERROR")
                .message("Error interno: " + ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(err));
    }
}
