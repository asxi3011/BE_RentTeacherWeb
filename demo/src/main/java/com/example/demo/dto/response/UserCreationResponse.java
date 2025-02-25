package com.example.demo.dto.response;

import com.example.demo.entity.Post;
import com.example.demo.entity.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationResponse {
     String id;
     String username;
     String firstName;
     String lastName;
     LocalDate dob;
     Set<Role> roles;
}
