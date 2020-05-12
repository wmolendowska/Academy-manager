package com.practice.model.repository;

import com.practice.model.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {
    List<Academy> findByName(String name);
    Boolean existsByName(String name);
    List<Academy> findAcademiesByNameContains(String name);
}
