package com.practice.model.repository;

import com.practice.model.entity.Course;
import com.practice.model.entity.FieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findCoursesByFieldsOfStudy(FieldOfStudy fieldOfStudy);
    List<Course> findCourseByNameContains(String name);
}
