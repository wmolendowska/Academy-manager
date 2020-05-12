package com.practice.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCourse;
    private String name;

    @ManyToMany(mappedBy = "courses")
    private List<FieldOfStudy> fieldsOfStudy;

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldOfStudy> getFieldsOfStudy() {
        return fieldsOfStudy;
    }

    public void setFieldsOfStudy(List<FieldOfStudy> fieldsOfStudy) {
        this.fieldsOfStudy = fieldsOfStudy;
    }

    public void removeFieldsOfStudy(List<FieldOfStudy> fieldsOfStudy){
        this.fieldsOfStudy.removeAll(fieldsOfStudy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(idCourse, course.idCourse) &&
                Objects.equals(name, course.name) &&
                Objects.equals(fieldsOfStudy, course.fieldsOfStudy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourse, name, fieldsOfStudy);
    }
}
