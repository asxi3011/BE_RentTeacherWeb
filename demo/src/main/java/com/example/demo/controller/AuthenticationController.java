package com.example.demo.controller;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.IntrospectRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.dto.response.IntrospectResponse;
import com.example.demo.service.AuthenticationService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest){

        AuthenticationResponse response = authenticationService.authentication(authenticationRequest);

        System.out.println(response);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(response)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest){
        IntrospectResponse response =authenticationService.introspect(introspectRequest);

        return ApiResponse.<IntrospectResponse>
                builder()
                .data(response)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<String> logout(@RequestBody IntrospectRequest introspectRequest){
        authenticationService.logout(introspectRequest);
        return ApiResponse.<String>
                        builder()
                .data("logout thanh cong")
                .build();
    }
}
