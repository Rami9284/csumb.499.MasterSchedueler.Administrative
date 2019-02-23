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
    private String teacherId;
    private int sectionNum;

    public Class(String department, String className) {
        this.department = department;
        this.className = className;
    }

    public Class(String id, String department, String className) {
        this.id = id;
        this.department = department;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(int sectionNum) {
        this.sectionNum = sectionNum;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", className='" + className + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", sectionNum=" + sectionNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return sectionNum == aClass.sectionNum &&
                id.equals(aClass.id) &&
                department.equals(aClass.department) &&
                className.equals(aClass.className) &&
                classRoom.equals(aClass.classRoom) &&
                teacherId.equals(aClass.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department, className, classRoom, teacherId, sectionNum);
    }
}
