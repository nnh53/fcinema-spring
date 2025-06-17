package com.react05.fcinema_spring.mapper;

import com.react05.fcinema_spring.entity.User;
import com.react05.fcinema_spring.model.request.User.UserRequest;
import com.react05.fcinema_spring.model.request.User.UserUpdate;
import com.react05.fcinema_spring.model.response.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);
    User updateUser(@MappingTarget User user,UserUpdate request );

}
