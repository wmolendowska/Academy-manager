package com.practice.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class FieldOfStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFieldOfStudy;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "fieldsOfStudyHasCourses",
            joinColumns = {@JoinColumn(name = "idFieldOfStudy")},
            inverseJoinColumns = {@JoinColumn(name = "idCourse")}
    )
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "idAcademy")
    private Academy academy;

    public List<Course> getCourses() {
        return courses;
    }

    public Long getIdFieldOfStudy() {
        return idFieldOfStudy;
    }

    public void setIdFieldOfStudy(Long idFieldOfStudy) {
        this.idFieldOfStudy = idFieldOfStudy;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course){
        this.courses.remove(course);
        course.getFieldsOfStudy().remove(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldOfStudy that = (FieldOfStudy) o;
        return Objects.equals(idFieldOfStudy, that.idFieldOfStudy) &&
                Objects.equals(name, that.name) &&
                Objects.equals(courses, that.courses) &&
                Objects.equals(academy, that.academy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFieldOfStudy, name, courses, academy);
    }
}
