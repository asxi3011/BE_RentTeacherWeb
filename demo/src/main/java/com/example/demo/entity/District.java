package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class District {

    @Id
    Integer id;
    String name;
    String divisionType;
    String codename;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    City city;
}
