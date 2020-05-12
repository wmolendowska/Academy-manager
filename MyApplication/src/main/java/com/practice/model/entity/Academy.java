package com.practice.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Academy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAcademy;
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "academy")
    private List<FieldOfStudy> fieldsOfStudy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return idAcademy.toString();
    }

    public Long getIdAcademy() {
        return idAcademy;
    }

    public List<FieldOfStudy> getFieldsOfStudy() {
        return fieldsOfStudy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Academy academy = (Academy) o;
        return Objects.equals(idAcademy, academy.idAcademy) &&
                Objects.equals(name, academy.name) &&
                Objects.equals(fieldsOfStudy, academy.fieldsOfStudy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAcademy, name, fieldsOfStudy);
    }
}
