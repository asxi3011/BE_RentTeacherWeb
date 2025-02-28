package com.example.demo.service;

import com.example.demo.dto.request.TeacherRequest;
import com.example.demo.dto.response.TeacherResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import com.example.demo.enums.Roles;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Builder
@Service
public class TeacherService {

    TeacherRepository teacherRepository;
    UserRepository userRepository;
    TeacherMapper teacherMapper;
    RoleRepository roleRepository;

    public TeacherResponse convertUserToTeacher( TeacherRequest request) {
        var context = SecurityContextHolder.getContext().getAuthentication();
        if(teacherRepository.existsById(context.getName()))
            throw new AppException(ErrorApp.TEACHER_ALREADY);
        Teacher teacher = teacherMapper.toTeacher(request);
        User user = userRepository.findByUsername(context.getName()).orElseThrow(()-> new AppException(ErrorApp.USER_NOT_FOUND));

        teacherMapper.updateTeacher(teacher,user);

        Role teacherRole = roleRepository.findById(Roles.TEACHER.name()).orElseThrow(() -> new AppException(ErrorApp.ROLES_NOT_FOUND));
        Set<Role> roles = teacher.getRoles();
        roles.add(teacherRole);
        teacher.setRoles(roles);
        userRepository.delete(user);
        //-------
        // sau khi xong chức năng đăng phải đăng xuất user và bắt đăng nhập lại để thoát token đang lưu trên user đã xóa
        return teacherMapper.toTeacherResponse(teacherRepository.save(teacher));
    }

    public TeacherResponse getMyTeacher(){
        var context = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = teacherRepository.findByUsername(context.getName()).orElseThrow(() -> new AppException(ErrorApp.USER_NOT_FOUND));
        return teacherMapper.toTeacherResponse(teacher);
    }


    public List<TeacherResponse> getAllTeacher(){
        return teacherRepository.findAll().stream().map(teacherMapper::toTeacherResponse).toList();
    }


}
