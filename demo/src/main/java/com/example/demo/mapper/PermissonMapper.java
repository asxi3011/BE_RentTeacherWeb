package com.example.demo.mapper;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissonResponse;
import com.example.demo.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissonMapper {

    Permission toPermission(PermissionRequest request);

    PermissonResponse toPermissionResponse(Permission permission);
}
