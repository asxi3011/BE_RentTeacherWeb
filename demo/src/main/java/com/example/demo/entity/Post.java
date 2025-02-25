package com.example.demo.entity;

import com.example.demo.enums.TypeTimeWork;
import com.example.demo.enums.WagePayments;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    String title;
    LocalDate datePost;
    LocalDate dateEnd;
    WagePayments wagePayment;
    TypeTimeWork typeTimeWork;
    int experience;
    BigDecimal wage;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user;
}
