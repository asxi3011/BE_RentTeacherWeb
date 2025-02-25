package com.example.demo.service;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissonResponse;
import com.example.demo.entity.Permission;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorApp;
import com.example.demo.mapper.PermissonMapper;
import com.example.demo.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.rmi.server.LogStream.log;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PermissionService {

    PermissionRepository repository;
    PermissonMapper permissonMapper;

    public PermissonResponse create(PermissionRequest request){
        Permission permission = permissonMapper.toPermission(request);
        repository.save(permission);
        return permissonMapper.toPermissionResponse(permission);
    }

    public List<PermissonResponse> getAll(){
        return repository.findAll().stream().map(permissonMapper::toPermissionResponse).toList();
    }

    public Permission getPermission(String name){
        return repository.findById(name).orElseThrow(
                ()-> new AppException(ErrorApp.PERMISSION_NOT_FOUND));
    }


    public void delete (String name){
        repository.deleteById(name);
    }

}
