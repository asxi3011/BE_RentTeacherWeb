package com.example.demo.repository;

import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,String> {
    Optional<Teacher> findByUsername(String username);
    boolean existsByUsername(String s);
}
