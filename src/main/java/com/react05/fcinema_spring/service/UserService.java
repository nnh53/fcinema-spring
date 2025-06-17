package com.react05.fcinema_spring.service;

import com.react05.fcinema_spring.entity.User;
import com.react05.fcinema_spring.model.request.User.UserRequest;
import com.react05.fcinema_spring.model.request.User.UserUpdate;
import com.react05.fcinema_spring.model.response.ApiResponse;

import java.util.List;

public interface UserService {
    ApiResponse<User> createUser(UserRequest user);
    ApiResponse<User> getUserById(String id);
    ApiResponse<Void> updateUser(String id, UserUpdate user);
    ApiResponse<Void> deleteUser(String id);
    ApiResponse<List<User>> findAllUsers();
}
