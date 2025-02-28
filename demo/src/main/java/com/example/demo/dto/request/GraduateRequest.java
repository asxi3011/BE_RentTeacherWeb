package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GraduateRequest {
    String major;
    String schoolYear;
    float gpa;
    Boolean isComplete;
    String codeUniversity;
    String nameUniversity;
}
