package com.example.demo.mapper;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserEditionRequest;
import com.example.demo.dto.response.UserCreationResponse;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper (componentModel = "spring")
public interface UserMapper {


    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget User user, UserEditionRequest request);

    UserCreationResponse toUserResponse(User user);
}
