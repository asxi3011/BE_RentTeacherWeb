package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Graduate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String major;
    String schoolYear;
    float gpa;
    Boolean isComplete;

    @ManyToOne
    @JoinColumn(name = "university_id",nullable = false)
    University university;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    Teacher teacher;


}
