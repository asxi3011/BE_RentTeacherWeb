package com.example.demo.controller;

import com.example.demo.dto.request.PostRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.PostResponse;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostController {

    PostService service;

    @PostMapping("/add")
    ApiResponse<PostResponse> create(@RequestBody PostRequest request){
        PostResponse response = service.create(request);
        return ApiResponse.<PostResponse>builder().data(response).build();
    }


    @GetMapping("/getAllPost")
    ApiResponse<List<PostResponse>> getAllPost(){
        List<PostResponse> response = service.getAll();
        return ApiResponse.<List<PostResponse>>builder().data(response).build();
    }
}
