package com.example.demo.service;

import com.example.demo.dto.request.UniversityRequest;
import com.example.demo.dto.response.UniversitiesDataResponse;
import com.example.demo.dto.response.UniversityResponse;
import com.example.demo.entity.University;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.UniversityMapper;
import com.example.demo.repository.UniversityRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UniversityService {

    UniversityRepository repository;
    UniversityMapper mapper;

    public UniversityResponse create(UniversityRequest request){
        University university = mapper.toUniversity(request);
        return mapper.toUniversityResponse(repository.save(university));
    }

    public UniversityResponse getUniversity(Integer id){
        University university = repository.findById(id).orElseThrow(()->new AppException(ErrorApp.UNIVERSITY_NOT_FOUND));
        return mapper.toUniversityResponse(university);
    }

    public List<UniversityResponse> getDataUniversity(){
        final String URL_DATA =  "https://diemthi.tuyensinh247.com/api/school/search";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UniversitiesDataResponse> response = restTemplate.getForEntity(URL_DATA,UniversitiesDataResponse.class);
        List<UniversityResponse> data = new ArrayList<>();
        if(Objects.nonNull(response.getBody())&&response.getBody().isSuccess()){
         data=response.getBody().getData();
        }
        return data;
    }


}

