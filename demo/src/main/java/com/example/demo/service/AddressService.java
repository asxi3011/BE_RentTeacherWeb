package com.example.demo.service;

import com.example.demo.dto.request.AddressRequest;
import com.example.demo.dto.response.AddressResponse;
import com.example.demo.entity.Address;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.repository.AddressRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AddressService {

    AddressRepository repository;
    AddressMapper mapper;
    public AddressResponse create(AddressRequest request){
        Address address = mapper.toAddress(request);
        repository.save(address);
        return mapper.toResponse(address);
    }
}
