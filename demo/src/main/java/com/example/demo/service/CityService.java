package com.example.demo.service;

import com.example.demo.dto.request.CityRequest;
import com.example.demo.dto.response.CityResponse;
import com.example.demo.entity.City;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.CityMapper;
import com.example.demo.repository.CityRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CityService {

    CityRepository repository;
    CityMapper mapper;

    public CityResponse create(CityRequest request){
        City city = mapper.toCity(request);
        return mapper.toCityResponse(repository.save(city));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<CityResponse> getAll(){
        var context = SecurityContextHolder.getContext().getAuthentication();
        return repository.findAll().stream().map(mapper::toCityResponse).toList();
    }


    public CityResponse getCity(Integer id){
        City city = repository.findById(id).orElseThrow(()->new AppException(ErrorApp.USER_NOT_FOUND));
        return mapper.toCityResponse(city);
    }

}
