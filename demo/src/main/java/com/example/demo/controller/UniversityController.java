package com.example.demo.controller;

import com.example.demo.dto.request.UniversityRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.UniversityResponse;
import com.example.demo.service.UniversityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/university")
public class UniversityController {

    UniversityService service;

    @PostMapping("/add")
    ApiResponse<UniversityResponse> create(@RequestBody UniversityRequest request){
        UniversityResponse response= service.create(request);
        return ApiResponse.<UniversityResponse>builder()
                .data(response)
                .build();
    }

    @PostMapping("/getUniversity/{id}")
    ApiResponse<UniversityResponse> create(@PathVariable Integer id){
        UniversityResponse response = service.getUniversity(id);
        return ApiResponse.<UniversityResponse>builder()
                .data(response)
                .build();
    }

    @GetMapping("/getUniversitiesOnline")
    ApiResponse<List<UniversityResponse>> getUniversitiesOnline(){
        List<UniversityResponse> response= service.getDataUniversity();
        return ApiResponse.<List<UniversityResponse>>builder()
                .data(response)
                .build();
    }

//    @GetMapping("/getUniversities")
//    ApiResponse<List<UniversityResponse>> getUniversities(){
//        List<UniversityResponse> response= service.getDataUniversity();
//        return ApiResponse.<List<UniversityResponse>>builder()
//                .data(response)
//                .build();
//    }
}
