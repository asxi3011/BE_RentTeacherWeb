package com.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateResponse {

    String trainingProgram;
    String reference;
    LocalDate issueDate;
    LocalDate expiryDate;
}
