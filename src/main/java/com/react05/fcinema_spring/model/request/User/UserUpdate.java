package com.react05.fcinema_spring.model.request.User;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdate {
    String fullName;
    String phone;
    String address;
    String avatar;
    String role;
    Boolean active;
}
