package com.example.demo.controller;

import com.example.demo.dto.request.TeacherRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.TeacherResponse;
import com.example.demo.service.TeacherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TeacherController {

    TeacherService service;

    @PostMapping("/add")
    ApiResponse<TeacherResponse> create (@RequestBody TeacherRequest request){
     TeacherResponse response = service.convertUserToTeacher(request);
     return ApiResponse.<TeacherResponse>builder()
             .data(response).build();
    }

    @GetMapping("/getAll")
    ApiResponse<List<TeacherResponse>> getAll(){
        List<TeacherResponse> responses = service.getAllTeacher();
        return ApiResponse.<List<TeacherResponse>>builder()
                .data(responses)
                .build();
    }

    @GetMapping("/getInfo")
    ApiResponse<TeacherResponse> getInfo(){
        TeacherResponse response = service.getMyTeacher();
        return ApiResponse.<TeacherResponse>builder()
                .data(response)
                .build();
    }
}
