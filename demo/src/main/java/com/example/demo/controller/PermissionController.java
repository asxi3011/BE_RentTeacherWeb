package com.example.demo.controller;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.PermissonResponse;
import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping("/create")
    ApiResponse<PermissonResponse> createPermission(@RequestBody PermissionRequest request) {
        PermissonResponse response = permissionService.create(request);
        return ApiResponse.<PermissonResponse>builder().data(response).build();
    }

    @GetMapping("/getAll")
    ApiResponse<List<PermissonResponse>> getAllPermission() {
        List<PermissonResponse> response = permissionService.getAll();
        return ApiResponse.<List<PermissonResponse>>builder().data(response).build();
    }

    @GetMapping("/hello")
    String hi(){
        return "HI";
    }


    @DeleteMapping("/delete/{name}")
    ApiResponse<Void> deletePermission(@PathVariable String name) {

        permissionService.delete(name);
        return ApiResponse.<Void>builder().build();

    }
}
