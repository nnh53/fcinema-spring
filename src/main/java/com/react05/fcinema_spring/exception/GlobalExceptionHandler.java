package com.react05.fcinema_spring.exception;

import com.react05.fcinema_spring.model.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";
    private static final String MAX_ATTRIBUTE = "max";

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingApptimeException(AppException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(exception.getErrorCode().getCode());
        apiResponse.setMessage(exception.getErrorCode().getMessage());
        HttpStatusCode status = exception.getErrorCode().getHttpStatusCode();
        return ResponseEntity.status(status).body(apiResponse);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, List<ApiResponse>>> handlingValidationException(MethodArgumentNotValidException exception) {
        List<ApiResponse> errors = exception.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            String enumKey = fieldError.getDefaultMessage();
            String fieldName = fieldError.getField();
            ErrorCode errorCode = ErrorCode.INVALID_KEY;
            Map attributes = null;

            try {
                errorCode = Arrays.stream(ErrorCode.values())
                        .filter(code -> code.name().equals(enumKey))
                        .findFirst()
                        .orElse(ErrorCode.INVALID_KEY);

                var constraintViolation = fieldError.unwrap(ConstraintViolation.class);
                attributes = constraintViolation.getConstraintDescriptor().getAttributes();
            } catch (Exception e) {
            }
            String errorMessage = Objects.nonNull(attributes) ? formatMessage(errorCode.getMessage(), attributes) : errorCode.getMessage();

            errorMessage = fieldName + ": " + errorMessage;
            return  ApiResponse.builder()
                    .code(errorCode.getCode())
                    .result(errorMessage)
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errors", errors));
    }
    private String formatMessage(String message, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }
        return message;
    }
}

