package com.example.demo.controller;

import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.control.MappingControl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleController {
    RoleService service;

    @PostMapping("/add")
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        RoleResponse response = service.create(request);
        return ApiResponse.<RoleResponse>builder()
                .data(response).build();
    }


    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse
                .<List<RoleResponse>>builder()
                .data(service.getAll())
                .build();
    }

    @DeleteMapping("/delete/{name}")
    ApiResponse<Void> delete(@PathVariable String name){
        service.delete(name);
        return ApiResponse
                .<Void>builder()
                .build();
    }
}
