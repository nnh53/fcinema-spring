package com.react05.fcinema_spring.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "User", description = "User entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    @Schema(description = "User ID", example = "uuid-1234")
    String id;

    @Column(name = "full_name", nullable = false)
    @Schema(description = "Full name", example = "John Doe")
    String fullName;

    @Column(name = "date_of_birth", nullable = true)
    @Schema(description = "Date of birth", type = "string", format = "date", example = "1990-01-01")
    LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, unique = true)
    @Schema(description = "Email address", example = "john@example.com")
    String email;

    @Column(name = "password")
    @Schema(description = "Password (hashed)", example = "hashedpassword")
    String password;

    @Column(name = "is_active", nullable = false)
    @Schema(description = "Is user active", example = "true")
    Boolean isActive = false;

    @Column(name = "is_subscription", nullable = true)
    @Schema(description = "Subscription status", example = "1")
    Integer isSubscription;

    @Column(name = "role_name")
    @Schema(description = "Role name", example = "CUSTOMER")
    RoleName role = RoleName.CUSTOMER;


    @Column(name = "avatar_url")
    String avatar;
    @Column(name = "loyalty_point", columnDefinition = "int default 0")
    Integer loyaltyPoint = 0;

    public enum RoleName {
        ADMIN, MANAGER, CUSTOMER, STAFF
    }
}
