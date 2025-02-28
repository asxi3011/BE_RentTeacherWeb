package com.example.demo.dto.response;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.Experience;
import com.example.demo.entity.Graduate;
import com.example.demo.enums.TypeTimeWork;
import com.example.demo.enums.WagePayments;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherResponse extends UserCreationResponse{
    String description;
    String image;
    String video;
    TypeTimeWork typeTimeWork;
    WagePayments wagePayments;

    List<PostResponse> posts;

    Set<GraduateResponse> graduates;

    Set<ExperienceResponse> experiences;

    Set<CertificateResponse> certificates;
}