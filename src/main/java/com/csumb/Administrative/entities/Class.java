package com.csumb.Administrative.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document
public class Class {

    @Id
    private String id;
    private String department;
    private String className;
    private String classRoom;

    public Class( ) {
        this.department = "";
        this.className = "";
        this.classRoom = "";
    }

    public Class(String department, String className) {
        this.department = department;
        this.className = className;
    }

    public Class(String department, String className, String classRoom) {
        this.department = department;
        this.className = className;
        this.classRoom = classRoom;
    }

    public Class(String department, String className, String classRoom, String id) {
        this.department = department;
        this.className = className;
        this.classRoom = classRoom;
        this.id = id;
    }

    public Class(Class c) {
        this.id = c.getid();
        this.department = c.getDepartment();
        this.className = c.getClassName();
        this.classRoom = c.getClassRoom();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", className='" + className + '\'' +
                ", classRoom='" + classRoom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Class)) return false;
        Class aClass = (Class) o;
        return id.equals(aClass.id) &&
                department.equals(aClass.department) &&
                className.equals(aClass.className) &&
                Objects.equals(classRoom, aClass.classRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department, className, classRoom);
    }
}