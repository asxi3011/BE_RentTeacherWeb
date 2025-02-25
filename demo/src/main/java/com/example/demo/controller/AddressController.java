package com.example.demo.controller;
import com.example.demo.dto.request.AddressRequest;
import com.example.demo.dto.response.AddressResponse;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AddressController {
    AddressService service;

    @PostMapping("/add")
    ApiResponse<AddressResponse> create(@RequestBody AddressRequest request){
        AddressResponse response = service.create(request);
        return ApiResponse.<AddressResponse>builder()
                .data(response)
                .build();
    }

    @GetMapping("/get_address_ui")
    ApiResponse<Object> getAll(){
        final String API_GET_CITY = "https://provinces.open-api.vn/api/?depth=2";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.getForEntity(API_GET_CITY, Object.class);
        log.warn(response.toString());
        return ApiResponse.<Object>builder()
                .data(response.getBody())
                .build();

    }


}
