package com.example.demo.mapper;

import com.example.demo.dto.request.ExperienceRequest;
import com.example.demo.dto.response.ExperienceResponse;
import com.example.demo.entity.Experience;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    Experience toExperience(ExperienceRequest request);

    ExperienceResponse toResponse(Experience experience);
}
