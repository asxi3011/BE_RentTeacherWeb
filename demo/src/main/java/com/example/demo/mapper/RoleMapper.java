package com.example.demo.mapper;

import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "Spring")
public interface RoleMapper {

    @Mapping(ignore = true,target = "permissions")
    Role toRole(RoleRequest request);

    RoleResponse toResponse(Role role);
}
