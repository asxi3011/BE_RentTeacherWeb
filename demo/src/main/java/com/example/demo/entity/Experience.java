package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nameCompany;
    String position;
    LocalDate startDate;
    LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    Teacher teacher;
}
