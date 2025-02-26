package com.example.demo.entity;

import com.example.demo.enums.TypeTimeWork;
import com.example.demo.enums.WagePayments;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name="user_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher extends User{
    String description;
    String image;
    String video;
    @Column(length = 10)
    TypeTimeWork typeTimeWork;
    @Column(length = 10)
    WagePayments wagePayments;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Graduate> graduates;

    public Teacher(User user, String description,String image,String video, TypeTimeWork typeTimeWork, WagePayments wagePayments) {
        super(user.getId(), user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getDob(),user.getRoles(),user.getPosts());
        this.description = description;
        this.image=image;
        this.video= video;
        this.typeTimeWork=typeTimeWork;
        this.wagePayments=wagePayments;
    }

}
