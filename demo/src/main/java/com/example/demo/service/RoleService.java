package com.example.demo.service;

import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class RoleService {
    RoleRepository repositoryRole;
    PermissionRepository repositoryPermission;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request){
        Role role = roleMapper.toRole(request);
        List<Permission> listPermission = repositoryPermission.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(listPermission));
        repositoryRole.save(role);
        return roleMapper.toResponse(role);
    }

    public List<RoleResponse> getAll(){
        return repositoryRole.findAll().stream().map(roleMapper::toResponse).toList();
    }

    public void delete(String name){
        repositoryRole.deleteById(name);
    }
}
