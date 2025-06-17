package com.react05.fcinema_spring.model.request.Authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotBlank(message ="NOT_EMPTY")
    String email;
    @NotBlank(message ="NOT_EMPTY")
    String password;
}
