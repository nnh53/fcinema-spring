package com.react05.fcinema_spring.entity;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    String id;

    @Column(name = "full_name", nullable = false)
    String fullName;

    @Column(name = "date_of_birth", nullable = true)
    LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "is_active", nullable = false)
    Boolean isActive = false;

    @Column(name = "is_subscription", nullable = true)
    Integer isSubscription;

    @Column(name = "role_name")
    RoleName role= RoleName.CUSTOMER;


    @Column(name = "avatar_url")
    String avatarUrl;

    @Column(name = "loyalty_point", columnDefinition = "int default 0")
    Integer loyaltyPoint = 0;

    public enum RoleName {
        ADMIN, MANAGER, CUSTOMER, STAFF
    }
}
