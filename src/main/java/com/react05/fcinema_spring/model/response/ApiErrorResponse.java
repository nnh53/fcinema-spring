package com.react05.fcinema_spring.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "ErrorResponse")
@Data
public class ApiErrorResponse {
    @Schema(description = "HTTP status code", example = "400")
    private int code;

    @Schema(description = "Error message", example = "Invalid input data")
    private String message;

    @Schema(description = "Error details")
    private Object result;

    // getters, setters, etc.
}