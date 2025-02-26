package com.example.demo.controller;

import com.example.demo.dto.request.GraduateRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.GraduateResponse;
import com.example.demo.entity.Graduate;
import com.example.demo.service.GraduateService;
import com.example.demo.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graduate")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class GraduateController {

    GraduateService service;

    @PostMapping("/add")
    ApiResponse<GraduateResponse> create(@RequestBody GraduateRequest request){
        GraduateResponse response = service.create(request);
        return ApiResponse.<GraduateResponse>builder()
                .data(response)
                .build();
    }

}
