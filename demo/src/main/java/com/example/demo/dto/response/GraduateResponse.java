package com.example.demo.dto.response;
import com.example.demo.entity.University;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GraduateResponse {
    String major;

    String schoolYear;

    float gpa;

    boolean isComplete;

    UniversityResponse university;
}