package com.example.demo.dto.response;

import com.example.demo.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String name;
    String description;
    Set<Permission> permissions;
}
