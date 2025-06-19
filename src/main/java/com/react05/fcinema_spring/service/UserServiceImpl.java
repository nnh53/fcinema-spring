package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.entity.User;
import com.react05.fcinema_spring.exception.AppException;
import com.react05.fcinema_spring.exception.ErrorCode;
import com.react05.fcinema_spring.mapper.UserMapper;
import com.react05.fcinema_spring.model.request.User.UserRequest;
import com.react05.fcinema_spring.model.request.User.UserUpdate;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    @Transactional
    public ApiResponse<User> createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(userRequest);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRole(User.RoleName.CUSTOMER);
        user.setIsActive(false);
        user.setLoyaltyPoint(0);
        User savedUser = userRepository.save(user);
        return ApiResponse.<User>builder()
                .code(200)
                .message("User created successfully")
                .result(savedUser)
                .build();
    }

    @Override
    public ApiResponse<List<User>> findAllUsers() {
        List<User> users = userRepository.findAll();
        return ApiResponse.<List<User>>builder()
                .code(200)
                .message("All users fetched successfully")
                .result(users)
                .build();
    }

    @Override
    public ApiResponse<User> getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return ApiResponse.<User>builder()
                .code(200)
                .message("User fetched successfully")
                .result(user)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> updateUser(String id, UserUpdate userUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, userUpdate);
        userRepository.save(user);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("User updated successfully")
                .build();
    }


    @Override
    @Transactional
    public ApiResponse<Void> deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("User deleted successfully")
                .build();
    }


    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
