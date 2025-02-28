package com.example.demo.service;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Collections.emptyMap());
            return uploadResult.get("secure_url").toString(); // Trả về URL ảnh đã upload
        } catch (IOException e) {
            throw new RuntimeException("Lỗi upload ảnh: " + e.getMessage());
        }
    }

}
