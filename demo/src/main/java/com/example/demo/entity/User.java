package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.example.demo.dto.response.ExperienceResponse;
import com.example.demo.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;
    private LocalDate dob;
    @ManyToMany
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public User(String username,String email, String firstName, String lastName, Set<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.email=email;
        this.lastName = lastName;
        this.roles = roles;
    }
}
