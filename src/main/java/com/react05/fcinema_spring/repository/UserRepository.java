package com.react05.fcinema_spring.repository;

import com.react05.fcinema_spring.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository  extends JpaRepository<User, String> {
    public Optional<User> findByEmail(@NotBlank String email);
    boolean existsByEmail(@NotBlank String email);
}
