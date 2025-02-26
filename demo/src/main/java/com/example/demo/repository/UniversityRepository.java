package com.example.demo.repository;

import com.example.demo.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {

    Optional<University> findByCode(String code);
}
