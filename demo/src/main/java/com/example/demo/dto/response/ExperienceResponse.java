package com.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExperienceResponse {
    String nameCompany;
    String position;
    LocalDate startDate;
    LocalDate endDate;
}
