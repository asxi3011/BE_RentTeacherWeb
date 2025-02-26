package com.example.demo.entity;

import com.example.demo.dto.response.UniversityResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 10, nullable = false)
    String code ;

    String name;

    String alias;

    @OneToMany(mappedBy = "university",cascade = CascadeType.ALL)
    Set<Graduate> graduates;

    public University(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
