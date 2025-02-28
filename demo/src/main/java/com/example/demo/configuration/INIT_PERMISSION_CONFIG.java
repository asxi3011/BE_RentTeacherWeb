package com.example.demo.configuration;

import com.example.demo.entity.Permission;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
@Configuration
@Getter
public class INIT_PERMISSION_CONFIG {

    private Set<Permission> userPermissions = new HashSet<>();
    private Set<Permission> teacherPermissions = new HashSet<>();


    public void init(){
        //USER PERMISSION;

        this.userPermissions.add(new Permission("CRUD_POST","You have permission CRUD_POST"));
        this.userPermissions.add(new Permission("CRUD_USER","You have permission CRUD_USER"));

        // TEACHER PERMISSION;

        this.teacherPermissions.add(new Permission("CREATE_UNIVERSITY","You have permission CREATE_UNIVERSITY"));
        this.teacherPermissions.add(new Permission("CREATE_GRADUATE","You have permission CREATE_GRADUATE"));
        this.teacherPermissions.add( new Permission("CREATE_EXPERIENCE","You have permission CREATE_EXPERIENCE"));
        this.teacherPermissions.add(new Permission("CREATE_CERTIFICATE","You have permission CREATE_CERTIFICATE"));
    }

}

