package com.react05.fcinema_spring.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults( level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name="CustomAPIResponse")
public class ApiResponse<T> {
    @Builder.Default
    @Schema(example = "200")
    int code = 200;
    @Schema(example = "Here is your message")
    String message;
    @Schema(description = "Result")
    T result;
}
