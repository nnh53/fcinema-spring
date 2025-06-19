package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.model.request.User.UserRequest;
import com.react05.fcinema_spring.model.request.User.UserUpdate;
import com.react05.fcinema_spring.model.response.ApiResponse;
import com.react05.fcinema_spring.model.response.Authentication.UserResponse;

import java.util.List;

public interface UserService {
    ApiResponse<UserResponse> createUser(UserRequest user);

    ApiResponse<UserResponse> getUserById(String id);

    ApiResponse<Void> updateUser(String id, UserUpdate user);

    ApiResponse<Void> deleteUser(String id);

    ApiResponse<List<UserResponse>> findAllUsers();
}
