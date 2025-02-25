package com.example.demo.service;

import com.example.demo.dto.request.DistrictRequest;
import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.entity.District;
import com.example.demo.mapper.DistrictMapper;
import com.example.demo.repository.DistrictRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Builder
public class DistrictService {

    DistrictRepository repository;
    DistrictMapper mapper;

    public DistrictResponse create(DistrictRequest request){
        District district = mapper.toDistrict(request);
        return mapper.toDistrictResponse(repository.save(district));
    }
}
