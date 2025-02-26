package com.example.demo.dto.request;

import com.example.demo.enums.TypeTimeWork;
import com.example.demo.enums.WagePayments;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherRequest {
    String description;
    String image;
    String video;

    TypeTimeWork typeTimeWork;

    WagePayments wagePayments;

}
