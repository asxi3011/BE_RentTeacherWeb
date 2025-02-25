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
                Permission permission = new Permission("APPROVE_POST", "You have permission approve post");
                Permission permission1 = new Permission("BAN_USER", "You have permission ban user");
                Permission permission2 = new Permission("DELETE_USER", "You have permission delete user");

                Set<Permission> listPermission= new HashSet<Permission>();
                listPermission.add(permission);
                listPermission.add(permission1);
                listPermission.add(permission2);
                Role role = new Role("ADMIN","You have role Admin",listPermission );
                Set<Role> roles= new HashSet<Role>();
                roles.add(role);

                permissionRepository.saveAll(listPermission);
                roleRepository.save(role);
                User user = new User("admin","admin","admin",roles);
                user.setPassword(passwordEncoder.encode("admin"));
                userRepository.save(user);
            }
        };
    }
}
