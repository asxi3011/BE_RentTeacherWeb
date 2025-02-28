package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.dto.request.TeacherRequest;
import com.example.demo.dto.request.UserEditionRequest;
import com.example.demo.dto.response.UserCreationResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.Teacher;
import com.example.demo.enums.Roles;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TeacherRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RequiredArgsConstructor
@FieldDefaults ( level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
@Service
public class UserService {

     UserRepository userRepository;
     RoleRepository roleRepository;

     PasswordEncoder passwordEncoder;
     UserMapper userMapper;
    private final TeacherRepository teacherRepository;


    public UserCreationResponse createRequest(UserCreationRequest userReq) {
        if(userRepository.existsByUsername(userReq.getUsername()) || userRepository.existsByEmail(userReq.getEmail()))
            throw new AppException(ErrorApp.USER_EXISTED);

        User user = userMapper.toUser(userReq);
        user.setPassword(passwordEncoder.encode(userReq.getPassword()));
        Role userRole =roleRepository.findById(Roles.USER.name()).orElseThrow(()->new AppException(ErrorApp.ROLES_NOT_FOUND));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setAvatar(generateAvatar(userReq.getFirstName()));
        return userMapper.toUserResponse(  userRepository.save(user));
    }

    public UserCreationResponse editRequest(UserEditionRequest userReq){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(
                ()-> new AppException(ErrorApp.USER_NOT_FOUND));
        userMapper.updateUser(user,userReq);
        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserCreationResponse> getListUser(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

     public UserCreationResponse getUser() {
         var authentication =  SecurityContextHolder.getContext().getAuthentication();

            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(
                    ()-> new AppException(ErrorApp.USER_NOT_FOUND));

         return userMapper.toUserResponse(user);
     }

    private String generateAvatar(String name){
        return "https://ui-avatars.com/api/?name="+name+"&background=random";
    }


     public void deleteUser(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
     }
}
