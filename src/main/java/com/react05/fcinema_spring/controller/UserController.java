package com.react05.fcinema_spring.controller;

import com.react05.fcinema_spring.entity.User;
import com.react05.fcinema_spring.model.request.User.UserRequest;
import com.react05.fcinema_spring.model.request.User.UserUpdate;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.service.UserService;
import com.react05.fcinema_spring.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable String userId, @RequestBody UserUpdate userRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }

}