package com.react05.fcinema_spring.model.request.User;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @NotBlank(message = "NOT_BLANK")
    @Email(message = "EMAIL_INVALID")
        String email;
    @NotBlank(message = "NAME_INVALID")
        String fullName;

    @NotBlank(message = "NOT_BLANK")
    @Size(min = 8, max = 20, message = "PASSWORD_INVALID")
        String password;
    String phone;

    String address;

    String avatar;

    String role;

    Boolean active;
}
