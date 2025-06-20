package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.User;
import com.react05.fcinema_spring.model.request.User.UserRequest;
import com.react05.fcinema_spring.model.request.User.UserUpdate;
import com.react05.fcinema_spring.model.response.Authentication.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    void updateUser(@MappingTarget User user, UserUpdate request);
}
