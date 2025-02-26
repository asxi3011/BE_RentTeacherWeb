package com.example.demo.controller;

import com.example.demo.dto.request.UserEditionRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UserCreationResponse;
import com.example.demo.entity.Teacher;
import com.example.demo.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/add")
    ApiResponse<UserCreationResponse> addUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserCreationResponse> response = new ApiResponse<>();
        response.setData(userService.createRequest(request));
        return response;
    }

    @GetMapping("/users")
    ApiResponse<List<UserCreationResponse>> getUser(){
        ApiResponse<List<UserCreationResponse>> response= new ApiResponse<>();
        response.setData(userService.getListUser());
        return response;
    }

    @GetMapping("/user")
    ApiResponse<UserCreationResponse> getUserById(){
        ApiResponse<UserCreationResponse> response = new ApiResponse<>();
        response.setData(userService.getUser());
        return response;
    }

    @PutMapping("/editUser")
    ApiResponse<UserCreationResponse> editUserById(@RequestBody @Valid UserEditionRequest request){
        ApiResponse<UserCreationResponse> response = new ApiResponse<>();
        UserCreationResponse dataS = userService.editRequest(request);
        response.setData(dataS);
        return response;
    }

    @DeleteMapping("/deleteUser")
    ApiResponse<String> deleteUserByID(){
        userService.deleteUser();
        ApiResponse<String> response = new ApiResponse<>();
        response.setData("user has been deleted");
        return response;
    }
}
