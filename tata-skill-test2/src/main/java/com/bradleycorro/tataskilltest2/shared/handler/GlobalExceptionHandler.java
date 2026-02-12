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
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler transforma todas las excepciones en ApiResponse.error estandarizados.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseDomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleDomain(BaseDomainException ex, WebRequest request) {
        var body = ApiResponse.<Void>error(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), getPath(request), null);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult binding = ex.getBindingResult();
        String message = binding.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
        var body = ApiResponse.<Void>error(HttpStatus.BAD_REQUEST, "Error de validación: " + message, getPath(request), null);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraint(ConstraintViolationException ex, WebRequest request) {
        var errors = ex.getConstraintViolations().stream()
                .map(cv -> new ErrorResponse("CONSTRAINT_VIOLATION", cv.getPropertyPath().toString(), cv.getMessage()))
                .toList();
        var body = ApiResponse.<Void>error(HttpStatus.UNPROCESSABLE_ENTITY, "Violación de restricciones", getPath(request), errors);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ApiResponse<Void>> handleNotFound(RuntimeException ex, WebRequest request) {
        var body = ApiResponse.<Void>error(HttpStatus.NOT_FOUND, ex.getMessage(), getPath(request), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex, WebRequest request) {
        var body = ApiResponse.<Void>error(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno: " + ex.getMessage(), getPath(request), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private String getPath(WebRequest request) {
        String desc = request.getDescription(false);
        // desc suele tener formato uri=/api/v1/...; extraemos tras "uri="
        int idx = desc.indexOf("uri=");
        return idx >= 0 ? desc.substring(idx + 4) : desc;
    }
}
