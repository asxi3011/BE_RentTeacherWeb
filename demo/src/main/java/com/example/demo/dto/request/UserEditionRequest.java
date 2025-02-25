package com.example.demo.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@FieldDefaults (level = AccessLevel.PRIVATE)
public class UserEditionRequest {
    @Size(min = 8 , message = "PASSWORD_INVALID")
     String password;
     String firstName;
     String lastName;
     LocalDate dob;

}
