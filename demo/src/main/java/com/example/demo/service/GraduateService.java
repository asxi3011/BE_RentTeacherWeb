package com.example.demo.service;

import com.example.demo.dto.request.GraduateRequest;
import com.example.demo.dto.response.GraduateResponse;
import com.example.demo.entity.Graduate;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.University;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.GraduateMapper;
import com.example.demo.repository.GraduateRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UniversityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class GraduateService {

    GraduateRepository repository;
    GraduateMapper mapper;
    TeacherRepository teacherRepository;
    UniversityRepository universityRepository;
    public GraduateResponse create(GraduateRequest request){
        var context = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = teacherRepository.findByUsername(context.getName()).orElseThrow(()-> new AppException(ErrorApp.USER_NOT_FOUND));
        if (teacher.getGraduates().size()>=2)
            throw new AppException(ErrorApp.FULL_GRADUATE);
        Graduate graduate = mapper.toGraduate(request);
        University university = universityRepository.findByCode(request.getCodeUniversity()).orElse(new University(request.getCodeUniversity(),request.getNameUniversity()));
        universityRepository.save(university);
        graduate.setTeacher(teacher);
        graduate.setUniversity(university);
        repository.save(graduate);
        return mapper.toResponse(graduate);
    }
}
