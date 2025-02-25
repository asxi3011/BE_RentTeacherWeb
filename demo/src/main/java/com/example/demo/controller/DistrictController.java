package com.example.demo.controller;

import com.example.demo.dto.request.DistrictRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.service.DistrictService;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/district")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DistrictController {

    DistrictService service;

    @PostMapping("/add")
    ApiResponse<DistrictResponse> create(@RequestBody DistrictRequest request){
        DistrictResponse response = service.create(request);
        return  ApiResponse.<DistrictResponse>builder().data(response).build();
    }

}
