package com.bradleycorro.tataskilltest2.shared.handler;

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

/**
 * GlobalExceptionHandler transforma todas las excepciones en ApiResponse. Error estandarizado.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseDomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleDomain(BaseDomainException ex) {
        var error = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type(ex.getCode())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        BindingResult binding = ex.getBindingResult();
        String message = binding.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");
        var error = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type("VALIDATION_ERROR")
                .message(message)
                .build();
        return ResponseEntity.badRequest().body(ApiResponse.error(error));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraint(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .findFirst()
                .orElse("Violación de restricciones");
        var error = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type("CONSTRAINT_VIOLATION")
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(error));
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ApiResponse<Void>> handleNotFound(RuntimeException ex) {
        var error = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .type("NOT_FOUND")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
        var error = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .type("INTERNAL_ERROR")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(error));
    }
}
