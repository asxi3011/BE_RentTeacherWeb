package com.example.demo.mapper;

import com.example.demo.dto.request.UniversityRequest;
import com.example.demo.dto.response.UniversityResponse;
import com.example.demo.entity.University;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UniversityMapper {
    University toUniversity(UniversityRequest request);

    UniversityResponse toUniversityResponse(University school);
}
