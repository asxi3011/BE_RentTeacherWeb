package com.example.demo.controller;

import com.example.demo.dto.request.CityRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.CityResponse;
import com.example.demo.service.CityService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/city")
public class CityController {
    CityService service;

    @PostMapping("/add")
    ApiResponse<CityResponse> create(@RequestBody CityRequest request){
        CityResponse response= service.create(request);
        return ApiResponse.<CityResponse>builder().data(response).build();
    }

    @GetMapping("/getAll")
    ApiResponse<List<CityResponse>> getAll (){
        List<CityResponse> response = service.getAll();
        return ApiResponse.<List<CityResponse>>builder().data(response).build();
    }

    @GetMapping ("/getCity/{id}")
    ApiResponse<CityResponse> getCity(@PathVariable Integer id){
        CityResponse response = service.getCity(id);
        return   ApiResponse.<CityResponse>builder()
                .data(response)
                .build();
    }
}
