package com.example.demo.dto.request;

import com.example.demo.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@FieldDefaults (level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size (min = 3, message = "USERNAME_INVALID")
     String username;
    @Size (min = 8 , message = "PASSWORD_INVALID")
     String password;
     String firstName;
     String lastName;
     @DobConstraint(min = 18,message = "DOB_INVALID")
     LocalDate dob;
     Set<String> role;
}
