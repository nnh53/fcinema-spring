package com.react05.fcinema_spring.model.response.Authentication;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
  String email;
  String fullName;
  String phone;
  String address;
  String avatar;
  String role;
  Boolean active;
}
