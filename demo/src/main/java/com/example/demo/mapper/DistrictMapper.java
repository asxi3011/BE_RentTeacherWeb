package com.example.demo.mapper;

import com.example.demo.dto.request.DistrictRequest;
import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.entity.District;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DistrictMapper {
    District toDistrict(DistrictRequest request);
    DistrictResponse toDistrictResponse (District response);
}
