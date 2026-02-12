package com.bradleycorro.tataskilltest2.shared.dto.responsegeneral.api;

/**
 * ErrorResponse representa un error individual dentro de una respuesta estandarizada.
 */
public class ErrorResponse {

    private final String code;
    private final String field;
    private final String message;

    public ErrorResponse(String code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getField() { return field; }
    public String getMessage() { return message; }
}
