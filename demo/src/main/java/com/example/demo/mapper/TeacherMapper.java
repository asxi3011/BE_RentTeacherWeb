package com.example.demo.mapper;

import com.example.demo.dto.request.TeacherRequest;
import com.example.demo.dto.request.UserEditionRequest;
import com.example.demo.dto.response.TeacherResponse;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    void updateTeacher(@MappingTarget Teacher teacher, User user);

    Teacher toTeacher(TeacherRequest request);
    TeacherResponse toTeacherResponse(Teacher teacher);
}
