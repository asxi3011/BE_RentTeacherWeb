package com.example.demo.mapper;

import com.example.demo.dto.request.GraduateRequest;
import com.example.demo.dto.response.GraduateResponse;
import com.example.demo.entity.Graduate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GraduateMapper {

    Graduate toGraduate(GraduateRequest request);

    GraduateResponse toResponse(Graduate graduate);
}
