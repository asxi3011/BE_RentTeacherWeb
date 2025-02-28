package com.example.demo.configuration;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import com.example.demo.enums.Roles;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class ApplicationConfig {


    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner (UserRepository userRepository, PermissionRepository permissionRepository, RoleRepository roleRepository){
        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()){
                INIT_PERMISSION_CONFIG listInit = new INIT_PERMISSION_CONFIG();
                listInit.init();
                Set<Role> roles = getRoles(listInit);
                permissionRepository.saveAll(listInit.getUserPermissions());
                permissionRepository.saveAll(listInit.getTeacherPermissions());
                roleRepository.saveAll(roles);
                User user = new User("admin","admin@gmail.com","admin","admin",roles);
                user.setPassword(passwordEncoder.encode("admin"));
                userRepository.save(user);
            }
        };
    }

    private static Set<Role> getRoles(INIT_PERMISSION_CONFIG listInit) {
        Set<Permission> userPermissions= listInit.getUserPermissions() ;
        Set<Permission> teacherPermission= listInit.getTeacherPermissions() ;

        Role roleAdmin = new Role("ADMIN","You have role Admin",new HashSet<>());
        Role roleUser = new Role(Roles.USER.name(),"You have role "+Roles.USER.name(),userPermissions);
        Role roleTeacher = new Role(Roles.TEACHER.name(),"You have role "+Roles.TEACHER.name(),teacherPermission);
        Set<Role> roles= new HashSet<Role>();
        roles.add(roleAdmin);
        roles.add(roleUser);
        roles.add(roleTeacher);
        return roles;
    }
}
