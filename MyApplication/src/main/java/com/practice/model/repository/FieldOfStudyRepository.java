package com.practice.model.repository;

import com.practice.model.entity.Academy;
import com.practice.model.entity.Course;
import com.practice.model.entity.FieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, Long>{
    Boolean existsByName(String name);
    List<FieldOfStudy> findFieldOfStudyByNameContains(String name);
    List<FieldOfStudy> findFieldOfStudiesByAcademy(Academy academy);
    List<FieldOfStudy> findFieldOfStudiesByAcademy_NameContains(String academyName);
    List<FieldOfStudy> removeFieldOfStudiesByCourses(Course course);
}
