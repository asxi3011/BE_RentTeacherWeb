package com.example.demo.controller;
import com.example.demo.dto.request.CertificateRequest;
import com.example.demo.dto.request.ExperienceRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.CertificateResponse;
import com.example.demo.dto.response.ExperienceResponse;
import com.example.demo.service.CertificateService;
import com.example.demo.service.ExperienceService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificate")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CertificateController {

    CertificateService service;

    @PostMapping("/add")
    ApiResponse<CertificateResponse> create(@RequestBody CertificateRequest request){
        CertificateResponse response = service.create(request);
            return ApiResponse.<CertificateResponse>builder()
                    .data(response)
                    .build();
    }

    @GetMapping("/{id}")
        ApiResponse<CertificateResponse> getInfo(@PathVariable Integer id){
        CertificateResponse response = service.getInfo(id);
            return ApiResponse.<CertificateResponse>builder()
                    .data(response)
                    .build();
        }
}
