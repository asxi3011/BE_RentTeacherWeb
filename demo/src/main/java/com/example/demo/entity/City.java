package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class City {

    @Id
    Integer id;
    String name;
    String divisionType;
    String codename;
    Integer phoneCode;

    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL,orphanRemoval = true)
    List<District> districts;


}
