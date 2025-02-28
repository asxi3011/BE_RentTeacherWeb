package com.example.demo.service;

import com.example.demo.dto.request.ExperienceRequest;
import com.example.demo.dto.response.ExperienceResponse;
import com.example.demo.entity.Experience;
import com.example.demo.entity.Teacher;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.ExperienceMapper;
import com.example.demo.repository.ExperienceRepository;
import com.example.demo.repository.TeacherRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ExperienceService {

    ExperienceRepository repository;
    ExperienceMapper mapper;
    TeacherRepository teacherRepository;
    public ExperienceResponse create(ExperienceRequest request){
        var context = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = teacherRepository.findByUsername(context.getName()).orElseThrow(()->new AppException(ErrorApp.USER_NOT_FOUND));
        Experience experience = mapper.toExperience(request);
        experience.setTeacher(teacher);
        return mapper.toResponse(repository.save(experience));
    }

    public ExperienceResponse getExperience(Integer id){
        return mapper.toResponse(
                repository.findById(id).orElseThrow(()->new AppException(ErrorApp.EXPERIENCE_NOT_FOUND))
        );
    }
}
