package com.example.demo.mapper;

import com.example.demo.dto.request.CityRequest;
import com.example.demo.dto.response.CityResponse;
import com.example.demo.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City toCity(CityRequest request);
    CityResponse toCityResponse(City city);
}
