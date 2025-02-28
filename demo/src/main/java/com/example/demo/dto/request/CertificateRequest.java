package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateRequest {

    String trainingProgram;
    String reference;
    LocalDate issueDate;
    LocalDate expiryDate;
}
