package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String trainingProgram;
    String reference;
    LocalDate issueDate;
    LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    Teacher teacher;
}
