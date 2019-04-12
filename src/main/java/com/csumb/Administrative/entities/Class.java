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
    private int maxNumStudentPerSection;

    public Class() {
        this.department = "";
        this.className = "";
        this.classRoom = "";
        maxNumStudentPerSection = 30;
    }

    public Class(String department, String className) {
        this.department = department;
        this.className = className;
        this.classRoom = "";
        maxNumStudentPerSection = 30;
    }

    public Class(String department, String className, String id) {
        this.department = department;
        this.className = className;
        this.id = id;
        this.classRoom = "";
        maxNumStudentPerSection = 30;
    }

    public Class(String department, String className, String id, int maxNumStudentPerSection){
        this.department = department;
        this.className = className;
        this.id = id;
        this.classRoom = "";
        this.maxNumStudentPerSection = maxNumStudentPerSection;
    }


    public Class(String department, String className, String roomNum, String id) {
        this.department = department;
        this.className = className;
        this.id = id;
        this.classRoom = roomNum;
        maxNumStudentPerSection = 30;
    }


    public Class(Class c) {
        this.id = c.getId();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxNumStudentPerSection() {
        return maxNumStudentPerSection;
    }

    public void setMaxNumStudentPerSection(int maxNumStudentPerSection) {
        this.maxNumStudentPerSection = maxNumStudentPerSection;
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
        return maxNumStudentPerSection == aClass.maxNumStudentPerSection &&
                id.equals(aClass.id) &&
                department.equals(aClass.department) &&
                className.equals(aClass.className) &&
                classRoom.equals(aClass.classRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department, className, classRoom, maxNumStudentPerSection);
    }
}