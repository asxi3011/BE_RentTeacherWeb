package com.example.demo.controller;

import com.example.demo.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {
    private final CloudinaryService cloudinaryService;

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = cloudinaryService.uploadFile(file);
        return ResponseEntity.ok(imageUrl);
    }
}

