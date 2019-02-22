package com.csumb.Administrative.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class Student {

    @Id
    String per_id;
    String name;
    int grade;
    List prefered_classes;

    public Student() {
        this.grade = 0;
        this.name = "test";
        this.per_id = "00000";
    }

    public Student(String per_id) {
        this.per_id = per_id;
    }

    public Student(String per_id, String name, int grade) {
        this.per_id = per_id;
        this.name = name;
        this.grade = grade;
    }

    public String getPer_id() {
        return per_id;
    }

    public void setPer_id(String per_id) {
        this.per_id = per_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List getprefered_classes() {
        return prefered_classes;
    }

    public void setprefered_classes(List prefered_classes) {
        this.prefered_classes = prefered_classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "per_id='" + per_id + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", prefered_classes=" + prefered_classes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return grade == student.grade &&
                per_id.equals(student.per_id) &&
                name.equals(student.name) &&
                prefered_classes.equals(student.prefered_classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(per_id, name, grade, prefered_classes);
    }
}
