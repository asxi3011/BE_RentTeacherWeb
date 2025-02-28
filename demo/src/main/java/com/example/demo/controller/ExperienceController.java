package com.example.demo.controller;

import com.example.demo.dto.request.ExperienceRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.ExperienceResponse;
import com.example.demo.service.ExperienceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ExperienceController {

    ExperienceService service;

    @PostMapping("/add")
    ApiResponse<ExperienceResponse> create(@RequestBody ExperienceRequest request){
        ExperienceResponse response = service.create(request);
        return ApiResponse.<ExperienceResponse>builder()
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ExperienceResponse> getInfo(@PathVariable Integer id){
        ExperienceResponse response = service.getExperience(id);
        return ApiResponse.<ExperienceResponse>builder()
                .data(response)
                .build();
    }
}
