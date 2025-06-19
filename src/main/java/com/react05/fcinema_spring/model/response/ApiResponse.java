package com.react05.fcinema_spring.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "CustomAPIResponse")
public class ApiResponse<T> {
    @Builder.Default
    @Schema(description = "HTTP status code", example = "200")
    int code = 200;
    @Schema(description = "Error message", example = "Invalid input data")
    String message;
    @Schema(
            description = "Result data. For user endpoints, this will be a User object or a list of User objects.",
            anyOf = {com.react05.fcinema_spring.entity.User.class, java.util.List.class}
    )
    T result;
}
